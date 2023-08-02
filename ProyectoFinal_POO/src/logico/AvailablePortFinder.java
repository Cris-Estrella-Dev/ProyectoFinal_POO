package logico;

import java.net.ServerSocket;
import java.io.IOException;

public class AvailablePortFinder {
    public static int findAvailablePort() {
        // Elige un rango de puertos que quieras verificar
        int startingPort = 8000;
        int endingPort = 9000;

        for (int port = startingPort; port <= endingPort; port++) {
            try {
                // Intenta crear un socket en el puerto dado.
                // Si el puerto está disponible, el socket se crea con éxito.
                // Si el puerto no está disponible, se lanzará una excepción y se continuará con el siguiente puerto.
                ServerSocket socket = new ServerSocket(port);
                socket.close();
                return port; // Puerto disponible encontrado
            } catch (IOException e) {
                // El puerto está ocupado, prueba el siguiente
            }
        }

        // Si no se encuentra ningún puerto disponible en el rango especificado, devuelve -1 o lanza una excepción personalizada.
        return -1;
    }

    public static void main(String[] args) {
        int availablePort = findAvailablePort();
        if (availablePort != -1) {
            System.out.println("Puerto disponible encontrado: " + availablePort);
        } else {
            System.out.println("No se encontraron puertos disponibles en el rango especificado.");
        }
    }
}
