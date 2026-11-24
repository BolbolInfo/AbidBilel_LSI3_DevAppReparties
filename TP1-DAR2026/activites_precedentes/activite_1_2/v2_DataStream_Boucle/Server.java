package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 1234;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            try (Socket socket = serverSocket.accept();
                 DataInputStream dis = new DataInputStream(socket.getInputStream());
                 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

                System.out.println("un client est connecté");

                while (true) {
                    int x = dis.readInt();
                    if (x == 0) {
                        System.out.println("Fermeture de la connexion...");
                        break;
                    }
                    int resultat = x * 5;
                    dos.writeInt(resultat);
                    dos.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
