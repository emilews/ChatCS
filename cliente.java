import java.io.*;
import java.net.*;
import java.util.*;

public class cliente {
    private static InetAddress host;
    private static int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("No se pudo obtener la IP local.");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;
        try {
            link = new Socket(host, PORT);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            Scanner userEntry = new Scanner(System.in);
            String msg, response;
            do {
                System.out.print("Introduzca un mensaje: ");
                msg = userEntry.nextLine();
                output.print(msg);
                response = input.nextLine();
                System.out.printf("\n\nSERVER> %s", response);
            } while (!msg.equals("***CLOSE***"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n*Cerrando la conexión*");
                link.close();
            }catch(IOException ioEx){
                System.out.println("No se ha podido desconectar correctamente");
                System.exit(1);
            }
        }
    }
}