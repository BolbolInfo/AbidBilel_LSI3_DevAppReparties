package etape2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientUDP {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName(
                    args.length > 0 ? args[0] : SERVER_HOST
            );

            System.out.print("Entrez votre nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine().trim();

            System.out.println("Tapez vos messages ('exit' pour quitter) :");
            while (true) {
                String texte = scanner.nextLine();
                if (texte.equalsIgnoreCase("exit")) {
                    break;
                }

                String message = "[" + nomUtilisateur + "] : " + texte;
                byte[] data = message.getBytes(StandardCharsets.UTF_8);

                DatagramPacket packet = new DatagramPacket(
                        data,
                        data.length,
                        serverAddress,
                        SERVER_PORT
                );
                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
