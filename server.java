import java.io.*;
import java.util.*;
import java.net.*;

public class server {
    private static ServerSocket servSock;
    private static int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Abriendo puerto....\n");
        try {
            servSock = new ServerSocket(PORT);
        } catch (IOException ioEx) {
            System.out.println("No se pudo abrir el servidor.");
            System.exit(1);

        }
        do {
            handleClient();
        } while (true);
    }

    private static void handleClient() {
        Socket link = null;
        try {
            link = servSock.accept();
            Scanner input = new Scanner(System.in);
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            int numMsg = 0;
            String msg = input.nextLine();
            while (!msg.equals("***CLOSE***")) {
                System.out.println("Mensaje recibido.");
                numMsg++;
                output.printf("Mensaje número %d : %s", numMsg, msg);
                msg = input.nextLine();
            }
            output.printf("%d mensajes recibidos.", numMsg);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n* Cerrando la conexión...*");
                link.close();
            } catch (IOException ioEx) {
                System.out.println("No se pudo desconectar correctamente.");
                System.exit(1);
            }
        }
    }
}