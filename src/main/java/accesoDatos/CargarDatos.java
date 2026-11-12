package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Operaciones de lectura sobre la base de datos. La mayoría vuelca el
 * resultado de una consulta directamente en un JTable.
 */
public class CargarDatos {

    /** Médico que inició sesión (null si el usuario actual no es un médico registrado). */
    private static String id_medico;
    private static String nombre_medico;

    private CargarDatos() {
    }

    public static void CargarConsulta(JTable jtable, String tablename) {
        llenarTabla(jtable, "SELECT * FROM " + tablename);
    }

    public static void CargarConsultaEspecifica(JTable jtable, String tablename, String columna, String condicion) {
        llenarTabla(jtable, "SELECT * FROM " + tablename + " WHERE " + columna + " = ?", condicion);
    }

    /** Citas del médico que inició sesión. La primera columna es el id de la cita. */
    public static void cargarCitasDelMedico(JTable jtable) {
        llenarTabla(jtable,
                "SELECT id_cita, cedula, nombre, telefono, direccion, fecha_cita FROM citas"
                        + " WHERE id_medico = ? ORDER BY id_cita",
                id_medico);
    }

    /** Pacientes (sin repetir) que tienen citas con el médico que inició sesión. */
    public static void cargarPacientesDelMedico(JTable jtable) {
        llenarTabla(jtable,
                "SELECT cedula, MAX(nombre) AS nombre, MAX(telefono) AS telefono, MAX(direccion) AS direccion,"
                        + " COUNT(*) AS citas FROM citas WHERE id_medico = ? GROUP BY cedula ORDER BY nombre",
                id_medico);
    }

    public static void cargarHistorialPaciente(JTable jtable, String cedula) {
        llenarTabla(jtable,
                "SELECT co.fecha_consulta AS fecha, m.nombre AS medico, m.especialidad AS especialidad,"
                        + " e.nombre AS enfermedad, COALESCE(v.nombre, '-') AS vacuna"
                        + " FROM consulta co"
                        + " JOIN citas c ON c.id_cita = co.id_cita"
                        + " JOIN medicos m ON m.id_medico = c.id_medico"
                        + " JOIN enfermedad e ON e.id_enfermedad = co.id_enfermedad"
                        + " LEFT JOIN vacuna v ON v.id_vacuna = co.id_vacuna"
                        + " WHERE c.cedula = ? ORDER BY co.id_consulta DESC",
                cedula);
    }

    public static void cargarEnfermedadesCronicasPaciente(JTable jtable, String cedula) {
        llenarTabla(jtable,
                "SELECT e.nombre AS enfermedad, MIN(co.fecha_consulta) AS diagnosticada"
                        + " FROM consulta co"
                        + " JOIN citas c ON c.id_cita = co.id_cita"
                        + " JOIN enfermedad e ON e.id_enfermedad = co.id_enfermedad"
                        + " WHERE c.cedula = ? AND e.cronico = 'Si'"
                        + " GROUP BY e.id_enfermedad, e.nombre ORDER BY e.nombre",
                cedula);
    }

    public static void cargarVacunasPaciente(JTable jtable, String cedula) {
        llenarTabla(jtable,
                "SELECT v.nombre AS vacuna, co.fecha_consulta AS fecha"
                        + " FROM consulta co"
                        + " JOIN citas c ON c.id_cita = co.id_cita"
                        + " JOIN vacuna v ON v.id_vacuna = co.id_vacuna"
                        + " WHERE c.cedula = ? ORDER BY co.id_consulta DESC",
                cedula);
    }

    /** Filas de médicos tal como se envían al servidor de respaldo. */
    public static void cargarMedicosParaRespaldo(JTable jtable) {
        llenarTabla(jtable, "SELECT cedula, nombre, especialidad, disponibilidad FROM medicos ORDER BY nombre");
    }

    /** Cantidad de citas por médico, para el gráfico del panel principal. */
    public static Map<String, Integer> citasPorMedico() {
        Map<String, Integer> resultado = new LinkedHashMap<>();
        String sql = "SELECT m.nombre, COUNT(*) AS total FROM citas c"
                + " JOIN medicos m ON m.id_medico = c.id_medico"
                + " GROUP BY m.id_medico, m.nombre ORDER BY total DESC";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            registrarError(e);
        }
        return resultado;
    }

    public static boolean existeRegistro(String cedula) {
        String sql = "SELECT 1 FROM paciente WHERE cedula = ?";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cedula);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    /**
     * Busca al médico cuya cédula coincide con la identificación del usuario
     * y lo deja como "médico en sesión".
     */
    public static void identificarMedico(String cedulamedico) {
        id_medico = null;
        nombre_medico = null;
        String sql = "SELECT id_medico, nombre FROM medicos WHERE cedula = ?";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cedulamedico);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id_medico = rs.getString("id_medico");
                    nombre_medico = rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            registrarError(e);
        }
    }

    public static void CargarComboBox(JComboBox<String> combobox) {
        String sql = "SELECT DISTINCT especialidad FROM medicos ORDER BY especialidad";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                combobox.addItem(rs.getString("especialidad"));
            }
        } catch (SQLException e) {
            registrarError(e);
        }
    }

    public static String getId_medico() {
        return id_medico;
    }

    public static String getNombre_medico() {
        return nombre_medico;
    }

    /**
     * Ejecuta la consulta y reemplaza el contenido de la tabla con el resultado.
     * Los encabezados salen de los nombres (o alias) de las columnas.
     */
    private static void llenarTabla(JTable jtable, String sql, Object... parametros) {
        DefaultTableModel model = new ModeloSoloLectura();
        try (Connection conexion = Conexion.abrir();
                PreparedStatement stmt = conexion.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaDatos = rs.getMetaData();
                int numColumnas = metaDatos.getColumnCount();
                for (int i = 1; i <= numColumnas; i++) {
                    model.addColumn(metaDatos.getColumnLabel(i).replace('_', ' ').toUpperCase());
                }
                while (rs.next()) {
                    Object[] fila = new Object[numColumnas];
                    for (int i = 0; i < numColumnas; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    model.addRow(fila);
                }
            }
        } catch (SQLException e) {
            registrarError(e);
        }
        jtable.setModel(model);
    }

    private static void registrarError(SQLException e) {
        System.err.println("Error de base de datos: " + e.getMessage());
    }

    /** Las tablas solo muestran datos: editar una celda no guardaría nada. */
    private static class ModeloSoloLectura extends DefaultTableModel {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
