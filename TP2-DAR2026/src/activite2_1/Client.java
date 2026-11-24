package activite2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1234;
    private static final Pattern OPERATION_PATTERN = Pattern.compile(
            "^\s*-?\d+(\.\d+)?\s+[+\-*/]\s+-?\d+(\.\d+)?\s*$"
    );

    public static void main(String[] args) {
        System.out.println("Je suis un client pas encore connecté...");
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("je suis un client connecté");

            while (true) {
                System.out.print("Entrez une opération (ex: 55 * 25) ou 'exit' pour quitter : ");
                String operation = scanner.nextLine().trim();

                if (operation.equalsIgnoreCase("exit")) {
                    pw.println("exit");
                    break;
                }

                if (!OPERATION_PATTERN.matcher(operation).matches()) {
                    System.out.println("Syntaxe invalide. Format attendu : <nombre> <+, -, *, /> <nombre>");
                    continue;
                }

                pw.println(operation);
                String reponse = br.readLine();
                System.out.println("Résultat = " + reponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
