package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int PORT = 1234;

        System.out.println("Je suis un client pas encore connecté...");
        try (Socket socket = new Socket(SERVER_IP, PORT);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("je suis un client connecté");

            while (true) {
                System.out.print("Donner un entier x (0 pour quitter) : ");
                int x = scanner.nextInt();

                dos.writeInt(x);
                dos.flush();

                if (x == 0) {
                    break;
                }

                int resultat = dis.readInt();
                System.out.println("Résultat (" + x + " * 5) = " + resultat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
