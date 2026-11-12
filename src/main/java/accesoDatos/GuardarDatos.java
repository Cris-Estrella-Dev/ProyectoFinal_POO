package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Operaciones de escritura sobre la base de datos.
 *
 * Todos los métodos devuelven true si la operación se guardó, para que la
 * interfaz pueda avisar al usuario cuando algo falla.
 */
public class GuardarDatos {

    private GuardarDatos() {
    }

    public static boolean insertarDatosMedico(String nombre, String cedula, String especialidad, String disponibilidad) {
        return ejecutar("INSERT INTO medicos (nombre, cedula, especialidad, disponibilidad) VALUES (?,?,?,?)",
                nombre, cedula, especialidad, disponibilidad);
    }

    public static boolean actualizarDatosMedico(int idMedico, String nombre, String cedula, String especialidad,
            String disponibilidad) {
        return ejecutar("UPDATE medicos SET nombre = ?, cedula = ?, especialidad = ?, disponibilidad = ? WHERE id_medico = ?",
                nombre, cedula, especialidad, disponibilidad, idMedico);
    }

    public static boolean eliminarMedico(int idMedico) {
        return ejecutar("DELETE FROM medicos WHERE id_medico = ?", idMedico);
    }

    public static boolean insertarDatosCita(String cedula, String nombre, String telefono, String direccion,
            String fecha, int idMedico) {
        return ejecutar("INSERT INTO citas (id_medico, cedula, nombre, telefono, direccion, fecha_cita) VALUES (?,?,?,?,?,?)",
                idMedico, cedula, nombre, telefono, direccion, fecha);
    }

    /**
     * @param idVacuna puede ser null: no en todas las consultas se aplica una vacuna.
     */
    public static boolean insertarDatosConsulta(int idCita, int idEnfermedad, Integer idVacuna, String fecha) {
        return ejecutar("INSERT INTO consulta (id_cita, id_enfermedad, id_vacuna, fecha_consulta) VALUES (?,?,?,?)",
                idCita, idEnfermedad, idVacuna, fecha);
    }

    public static boolean insertarDatosPaciente(String nombre, String cedula, String direccion, String telefono) {
        return ejecutar("INSERT INTO paciente (nombre, cedula, direccion, telefono) VALUES (?,?,?,?)",
                nombre, cedula, direccion, telefono);
    }

    public static boolean insertarDatosEnfermedad(String nombre, String cronico) {
        return ejecutar("INSERT INTO enfermedad (nombre, cronico) VALUES (?,?)", nombre, cronico);
    }

    public static boolean insertarDatosVacuna(String nombre) {
        return ejecutar("INSERT INTO vacuna (nombre) VALUES (?)", nombre);
    }

    public static boolean insertarDatosUsuario(String identificacion, String clave, String tipoUsuario) {
        return ejecutar("INSERT INTO usuarios (identificacion, clave, tipo_usuario) VALUES (?,?,?)",
                identificacion, clave, tipoUsuario);
    }

    public static boolean verificarInicioSesion(String identificacion, String clave, String tipoUsuario) {
        String sql = "SELECT 1 FROM usuarios WHERE identificacion = ? AND clave = ? AND tipo_usuario = ?";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, identificacion);
            statement.setString(2, clave);
            statement.setString(3, tipoUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    private static void registrarError(SQLException e) {
        System.err.println("Error de base de datos: " + e.getMessage());
    }

    private static boolean ejecutar(String sql, Object... parametros) {
        try (Connection conexion = Conexion.abrir();
                PreparedStatement statement = conexion.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                statement.setObject(i + 1, parametros[i]);
            }
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }
}
