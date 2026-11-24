package activite3_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 1234;
    private static int compteurClients = 0;
    private static int compteurOperations = 0;

    public static synchronized int incrementerCompteurOperations() {
        compteurOperations++;
        return compteurOperations;
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur calculatrice multi-thread en attente sur le port " + PORT + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                int numeroClient = ++compteurClients;
                System.out.println("Client n°" + numeroClient + " connecté depuis "
                        + socket.getRemoteSocketAddress());
                new ClientProcess(socket, numeroClient).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
