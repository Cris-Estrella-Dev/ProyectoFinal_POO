package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class GuardarDatos {

    public static void insertarDatos(String campo1, String campo2, String campo3, String campo4) {
        Connection conexion = Conexion.obtenerConexion();
        

        if (conexion != null) {
            try {
                String sql = "INSERT INTO Medicos (nombre, cedula,especialidad,disponibilidad) VALUES (?,?,?,?)";
                PreparedStatement statement = conexion.prepareStatement(sql);

                statement.setString(1, campo1);
                statement.setString(2, campo2);
                statement.setString(3, campo3);
                statement.setString(4, campo4);

                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Registro insertado correctamente.");
                   
                } else {
                    System.out.println("No se pudo insertar el registro.");
                }

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Error al establecer la conexión.");
        }

        
    }
}
