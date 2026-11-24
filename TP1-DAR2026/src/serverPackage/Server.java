package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 1234;
    private static final double CONSTANTE = 5.0;

    public static void main(String[] args) {
        try {
            InetAddress serverAddress = (args.length > 0)
                    ? InetAddress.getByName(args[0])
                    : InetAddress.getLocalHost();

            InetSocketAddress socketAddress = new InetSocketAddress(serverAddress, PORT);

            try (ServerSocket serverSocket = new ServerSocket()) {
                serverSocket.bind(socketAddress);
                System.out.println("Je suis un serveur en attente la connexion d'un client sur "
                        + serverAddress.getHostAddress() + ":" + PORT);

                try (Socket socket = serverSocket.accept();
                     DataInputStream dis = new DataInputStream(socket.getInputStream());
                     DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("un client est connecté");

                    while (true) {
                        int choix = dis.readInt();
                        if (choix == 0) {
                            break;
                        }

                        double x = dis.readDouble();
                        double resultat;

                        switch (choix) {
                            case 1:
                                resultat = x + CONSTANTE;
                                break;
                            case 2:
                                resultat = x - CONSTANTE;
                                break;
                            case 3:
                                resultat = x * CONSTANTE;
                                break;
                            case 4:
                                resultat = x / CONSTANTE;
                                break;
                            default:
                                resultat = Double.NaN;
                                break;
                        }

                        dos.writeDouble(resultat);
                        dos.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
