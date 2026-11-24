package etape3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientChatUDP {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 1234;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName(
                    args.length > 0 ? args[0] : SERVER_HOST
            );

            System.out.print("Entrez votre nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine().trim();

            Thread receiver = new Thread(() -> {
                byte[] buffer = new byte[BUFFER_SIZE];
                while (!socket.isClosed()) {
                    try {
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);
                        String recu = new String(
                                packet.getData(),
                                0,
                                packet.getLength(),
                                StandardCharsets.UTF_8
                        );
                        System.out.println(recu);
                    } catch (IOException e) {
                        break;
                    }
                }
            });
            receiver.setDaemon(true);
            receiver.start();

            System.out.println("Chat démarré ('exit' pour quitter) :");
            while (true) {
                String texte = scanner.nextLine();
                if (texte.equalsIgnoreCase("exit")) {
                    socket.close();
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
