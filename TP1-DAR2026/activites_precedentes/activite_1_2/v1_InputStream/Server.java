package serverPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 1234;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            try (Socket socket = serverSocket.accept()) {
                System.out.println("un client est connecté");

                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                int x = is.read();
                int resultat = x * 5;

                os.write(resultat);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
