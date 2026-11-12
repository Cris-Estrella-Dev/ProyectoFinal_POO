package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Punto único de acceso a la base de datos.
 *
 * La configuración se toma de variables de entorno para no dejar credenciales
 * en el código. Los valores por defecto coinciden con la base de datos de
 * desarrollo definida en docker-compose.yml.
 */
public class Conexion {
    private static final String URL = configuracion("DB_URL", "jdbc:mysql://localhost:3306/clinicasunny");
    private static final String USUARIO = configuracion("DB_USER", "clinica");
    private static final String CONTRASENA = configuracion("DB_PASSWORD", "clinica");

    private Conexion() {
    }

    /**
     * Abre una conexión nueva. Quien la pide es responsable de cerrarla
     * (lo normal es usarla dentro de un try-with-resources).
     */
    public static Connection abrir() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    /**
     * Comprueba si la base de datos responde.
     *
     * @return null si todo está bien, o el motivo del fallo.
     */
    public static String probar() {
        try (Connection conexion = abrir()) {
            return conexion.isValid(5) ? null : "La base de datos no responde.";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public static String getUrl() {
        return URL;
    }

    private static String configuracion(String nombre, String porDefecto) {
        String valor = System.getenv(nombre);
        return (valor == null || valor.trim().isEmpty()) ? porDefecto : valor.trim();
    }
}
