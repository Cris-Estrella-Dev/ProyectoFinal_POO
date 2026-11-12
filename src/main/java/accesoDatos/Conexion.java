package accesoDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/clinicasunny";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "cristiancule";
    private static Connection conexion;

    public static Connection obtenerConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    } 
}  
