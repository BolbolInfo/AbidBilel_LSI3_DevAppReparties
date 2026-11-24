package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 1234;
        try {
            System.out.println("Je suis un client pas encore connecté...");
            Socket socket = new Socket(HOST, PORT);
            System.out.println("je suis un client connecté");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
