package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 1234;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Je suis un serveur en attente la connexion d'un client ");

            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté");

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
