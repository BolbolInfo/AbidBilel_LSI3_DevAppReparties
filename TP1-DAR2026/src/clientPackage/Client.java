package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String serverIp;
            if (args.length > 0) {
                serverIp = args[0];
            } else {
                System.out.print("Adresse IP du serveur (vide pour l'IP locale) : ");
                serverIp = scanner.nextLine().trim();
                if (serverIp.isEmpty()) {
                    serverIp = InetAddress.getLocalHost().getHostAddress();
                }
            }

            InetAddress serverAddress = InetAddress.getByName(serverIp);
            InetSocketAddress socketAddress = new InetSocketAddress(serverAddress, PORT);

            System.out.println("Je suis un client pas encore connecté...");
            try (Socket socket = new Socket()) {
                socket.connect(socketAddress);
                System.out.println("je suis un client connecté");

                try (DataInputStream dis = new DataInputStream(socket.getInputStream());
                     DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

                    while (true) {
                        System.out.println("\n1. Addition (x + 5)");
                        System.out.println("2. Soustraction (x - 5)");
                        System.out.println("3. Multiplication (x * 5)");
                        System.out.println("4. Division (x / 5)");
                        System.out.println("0. Quitter");
                        System.out.print("Choix : ");

                        int choix = scanner.nextInt();

                        if (choix == 0) {
                            dos.writeInt(0);
                            dos.flush();
                            break;
                        }

                        if (choix < 1 || choix > 4) {
                            System.out.println("Choix invalide.");
                            continue;
                        }

                        System.out.print("Valeur de x : ");
                        double x = scanner.nextDouble();

                        dos.writeInt(choix);
                        dos.writeDouble(x);
                        dos.flush();

                        double resultat = dis.readDouble();
                        System.out.println("Résultat = " + resultat);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
