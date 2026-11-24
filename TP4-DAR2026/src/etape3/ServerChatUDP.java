package etape3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class ServerChatUDP {
    private static final int PORT = 1234;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        Set<SocketAddress> clients = new HashSet<>();
        InetSocketAddress serverAddress = new InetSocketAddress(PORT);

        try (DatagramSocket socket = new DatagramSocket(serverAddress)) {
            System.out.println("Serveur de chat UDP démarré sur le port " + PORT + "...");
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                SocketAddress expediteur = packet.getSocketAddress();
                clients.add(expediteur);

                String message = new String(
                        packet.getData(),
                        0,
                        packet.getLength(),
                        StandardCharsets.UTF_8
                );

                System.out.println("De " + expediteur + " -> " + message);

                byte[] data = message.getBytes(StandardCharsets.UTF_8);
                for (SocketAddress client : clients) {
                    if (!client.equals(expediteur)) {
                        DatagramPacket forwardPacket = new DatagramPacket(data, data.length, client);
                        socket.send(forwardPacket);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
