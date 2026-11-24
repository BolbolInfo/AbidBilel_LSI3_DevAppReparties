package etape2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class ServerUDP {
    private static final int PORT = 1234;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress(PORT);
        try (DatagramSocket socket = new DatagramSocket(address)) {
            System.out.println("Serveur UDP en écoute sur le port " + PORT + "...");
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(
                        packet.getData(),
                        0,
                        packet.getLength(),
                        StandardCharsets.UTF_8
                );

                System.out.println("Reçu de " + packet.getAddress().getHostAddress()
                        + ":" + packet.getPort() + " -> " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
