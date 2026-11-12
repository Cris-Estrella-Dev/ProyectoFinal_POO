package visual;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Las pantallas dependen de sus imágenes de fondo. Antes se cargaban con rutas
 * absolutas de una máquina concreta; ahora deben estar siempre en el classpath.
 */
class RecursosTest {

    @Test
    void todasLasImagenesDeFondoEstanEnElClasspath() {
        String[] imagenes = { Recursos.LOGIN, Recursos.PRINCIPAL, Recursos.MENU, Recursos.CONSULTA,
                Recursos.REGISTRAR_CITA, Recursos.MIS_PACIENTES, Recursos.PERFIL_MEDICO };
        for (String imagen : imagenes) {
            assertNotNull(Recursos.class.getResource("/imagenes/" + imagen), "Falta la imagen " + imagen);
        }
    }

    @Test
    void unaImagenInexistenteFallaConUnMensajeClaro() {
        assertThrows(IllegalStateException.class, () -> Recursos.imagen("no-existe.png"));
    }
}
