package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int PORT = 1234;
        try {
            System.out.println("Je suis un client pas encore connecté...");
            Socket socket = new Socket(SERVER_IP, PORT);
            System.out.println("je suis un client connecté");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
