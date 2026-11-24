package activite3_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Client {
    private static final int PORT = 1234;
    private static final Pattern OPERATION_PATTERN = Pattern.compile(
            "^\\s*-?\\d+(\\.\\d+)?\\s+[+\\-*/]\\s+-?\\d+(\\.\\d+)?\\s*$"
    );

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String serverIp;
            if (args.length > 0) {
                serverIp = args[0];
            } else {
                System.out.print("Adresse IP du serveur (vide pour 127.0.0.1) : ");
                serverIp = scanner.nextLine().trim();
                if (serverIp.isEmpty()) {
                    serverIp = "127.0.0.1";
                }
            }

            InetAddress serverAddress = InetAddress.getByName(serverIp);
            InetSocketAddress socketAddress = new InetSocketAddress(serverAddress, PORT);

            try (Socket socket = new Socket()) {
                socket.connect(socketAddress);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

                    String accueil = br.readLine();
                    System.out.println(accueil);

                    while (true) {
                        System.out.print("Entrez une opération (ex: 55 * 25) ou 'exit' : ");
                        String operation = scanner.nextLine().trim();

                        if (operation.equalsIgnoreCase("exit")) {
                            pw.println("exit");
                            break;
                        }

                        if (!OPERATION_PATTERN.matcher(operation).matches()) {
                            System.out.println("Syntaxe invalide.");
                            continue;
                        }

                        pw.println(operation);
                        System.out.println("Résultat = " + br.readLine());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
