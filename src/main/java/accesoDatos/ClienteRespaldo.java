package accesoDatos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

/** Cliente del servidor de respaldo ({@link MiServidor}). */
public class ClienteRespaldo {

    private ClienteRespaldo() {
    }

    /**
     * Envía las líneas al servidor de respaldo de esta máquina.
     *
     * @return la ruta del archivo donde el servidor guardó el respaldo.
     * @throws IOException si no hay servidor escuchando o si el servidor no pudo guardar.
     */
    public static String enviar(int puerto, List<String> lineas) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), puerto)) {
            DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream entrada = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            salida.writeInt(lineas.size());
            for (String linea : lineas) {
                salida.writeUTF(linea);
            }
            salida.flush();

            String respuesta = entrada.readUTF();
            if (!respuesta.startsWith("OK ")) {
                throw new IOException(respuesta);
            }
            return respuesta.substring(3);
        }
    }
}
