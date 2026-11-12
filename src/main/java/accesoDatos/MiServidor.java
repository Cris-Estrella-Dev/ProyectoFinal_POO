package accesoDatos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor de respaldo. Recibe por socket las filas a respaldar y las escribe
 * en un archivo de texto.
 *
 * Protocolo: el cliente envía un entero con la cantidad de líneas y luego cada
 * línea con writeUTF; el servidor responde "OK ruta-del-archivo" o "ERROR motivo".
 *
 * La aplicación lo arranca sola en segundo plano, pero también se puede
 * ejecutar por separado con su propio main.
 */
public class MiServidor extends Thread {

    public static final int PUERTO = 8014;
    public static final String ARCHIVO_RESPALDO = "respaldos/medicos_respaldo.txt";

    private final ServerSocket servidor;
    private final File archivo;

    public MiServidor(ServerSocket servidor, File archivo) {
        super("servidor-respaldo");
        this.servidor = servidor;
        this.archivo = archivo;
        setDaemon(true);
    }

    /**
     * Arranca el servidor dentro de la propia aplicación.
     *
     * @return false si el puerto ya está en uso (por ejemplo, porque el
     *         servidor ya se está ejecutando por separado).
     */
    public static boolean iniciarEnSegundoPlano() {
        try {
            new MiServidor(abrirSocket(PUERTO), new File(ARCHIVO_RESPALDO)).start();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /** Solo acepta conexiones desde la propia máquina. */
    private static ServerSocket abrirSocket(int puerto) throws IOException {
        return new ServerSocket(puerto, 50, InetAddress.getLoopbackAddress());
    }

    @Override
    public void run() {
        while (!servidor.isClosed()) {
            try (Socket cliente = servidor.accept()) {
                atender(cliente);
            } catch (IOException e) {
                if (!servidor.isClosed()) {
                    System.out.println("Error en el servidor de respaldo: " + e);
                }
            }
        }
    }

    private void atender(Socket cliente) throws IOException {
        DataInputStream entrada = new DataInputStream(new BufferedInputStream(cliente.getInputStream()));
        DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(cliente.getOutputStream()));

        int cantidad = entrada.readInt();
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            lineas.add(entrada.readUTF());
        }

        try {
            File carpeta = archivo.getAbsoluteFile().getParentFile();
            if (carpeta != null) {
                Files.createDirectories(carpeta.toPath());
            }
            Files.write(archivo.toPath(), lineas, StandardCharsets.UTF_8);
            salida.writeUTF("OK " + archivo.getAbsolutePath());
        } catch (IOException e) {
            salida.writeUTF("ERROR " + e.getMessage());
        }
        salida.flush();
    }

    public static void main(String[] args) {
        try {
            MiServidor servidor = new MiServidor(abrirSocket(PUERTO), new File(ARCHIVO_RESPALDO));
            System.out.println("Servidor de respaldo escuchando en el puerto " + PUERTO);
            servidor.run();
        } catch (IOException ioe) {
            System.out.println("No se pudo iniciar el servidor de respaldo: " + ioe);
            System.exit(1);
        }
    }
}
