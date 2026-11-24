package clientPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int PORT = 1234;

        System.out.println("Je suis un client pas encore connecté...");
        try (Socket socket = new Socket(SERVER_IP, PORT);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("je suis un client connecté");

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            System.out.print("Donner un entier x : ");
            int x = scanner.nextInt();

            os.write(x);
            os.flush();

            int resultat = is.read();
            System.out.println("Résultat (" + x + " * 5) = " + resultat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
