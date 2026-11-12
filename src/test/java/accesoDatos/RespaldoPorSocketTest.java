package accesoDatos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** El respaldo viaja por socket del cliente al servidor, que lo escribe en disco. */
class RespaldoPorSocketTest {

    @TempDir
    Path carpeta;

    @Test
    void elServidorGuardaLasLineasQueEnviaElCliente() throws IOException {
        File archivo = carpeta.resolve("respaldos").resolve("medicos_respaldo.txt").toFile();
        try (ServerSocket socket = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            new MiServidor(socket, archivo).start();

            List<String> lineas = Arrays.asList(
                    "40200000001    Laura Méndez    Cardiología    Lunes y miércoles",
                    "40200000002    Pedro Santana    Pediatría    Martes y jueves");
            String ruta = ClienteRespaldo.enviar(socket.getLocalPort(), lineas);

            assertEquals(archivo.getAbsolutePath(), ruta);
            assertEquals(lineas, Files.readAllLines(archivo.toPath(), StandardCharsets.UTF_8));
        }
    }

    @Test
    void cadaRespaldoReemplazaAlAnterior() throws IOException {
        File archivo = carpeta.resolve("medicos_respaldo.txt").toFile();
        try (ServerSocket socket = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            new MiServidor(socket, archivo).start();

            ClienteRespaldo.enviar(socket.getLocalPort(), Arrays.asList("uno", "dos", "tres"));
            ClienteRespaldo.enviar(socket.getLocalPort(), Collections.singletonList("solo uno"));

            assertEquals(Collections.singletonList("solo uno"),
                    Files.readAllLines(archivo.toPath(), StandardCharsets.UTF_8));
        }
    }

    @Test
    void sinServidorElClienteFallaConIOException() throws IOException {
        int puertoLibre;
        try (ServerSocket socket = new ServerSocket(0, 50, InetAddress.getLoopbackAddress())) {
            puertoLibre = socket.getLocalPort();
        }
        IOException error = assertThrows(IOException.class,
                () -> ClienteRespaldo.enviar(puertoLibre, Collections.singletonList("x")));
        assertTrue(error.getMessage() != null && !error.getMessage().isEmpty());
    }
}
