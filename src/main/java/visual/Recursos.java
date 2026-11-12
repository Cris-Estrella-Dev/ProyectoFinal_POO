package visual;

import java.awt.Cursor;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Carga las imágenes desde el classpath (src/main/resources/imagenes), de modo
 * que la aplicación funcione en cualquier máquina y también empaquetada en un JAR.
 */
final class Recursos {

    static final String LOGIN = "login.png";
    static final String PRINCIPAL = "principal.png";
    static final String MENU = "menu.png";
    static final String CONSULTA = "consulta.png";
    static final String REGISTRAR_CITA = "registrar-cita.png";
    static final String MIS_PACIENTES = "mis-pacientes.png";
    static final String PERFIL_MEDICO = "perfil-medico.png";

    private Recursos() {
    }

    static ImageIcon imagen(String nombre) {
        URL url = Recursos.class.getResource("/imagenes/" + nombre);
        if (url == null) {
            throw new IllegalStateException("No se encontró la imagen /imagenes/" + nombre);
        }
        return new ImageIcon(url);
    }

    /**
     * Los botones dibujados en las imágenes de fondo se activan con un JButton
     * transparente colocado encima. Esto lo deja invisible en cualquier
     * Look and Feel (no solo en macOS).
     */
    static JButton zonaClic(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setBorder(null);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return boton;
    }
}
