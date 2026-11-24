package activite2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            try (Socket socket = serverSocket.accept();
                 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

                System.out.println("un client est connecté");

                String ligne;
                while ((ligne = br.readLine()) != null) {
                    if (ligne.equalsIgnoreCase("exit")) {
                        break;
                    }

                    String[] parts = ligne.trim().split("\s+");
                    if (parts.length != 3) {
                        pw.println("Erreur : format invalide");
                        continue;
                    }

                    try {
                        double op1 = Double.parseDouble(parts[0]);
                        String operateur = parts[1];
                        double op2 = Double.parseDouble(parts[2]);

                        if (operateur.equals("/") && op2 == 0) {
                            pw.println("Erreur : division par zéro");
                            continue;
                        }

                        double resultat;
                        switch (operateur) {
                            case "+":
                                resultat = op1 + op2;
                                break;
                            case "-":
                                resultat = op1 - op2;
                                break;
                            case "*":
                                resultat = op1 * op2;
                                break;
                            case "/":
                                resultat = op1 / op2;
                                break;
                            default:
                                pw.println("Erreur : opérateur non supporté");
                                continue;
                        }

                        pw.println(resultat);
                    } catch (NumberFormatException e) {
                        pw.println("Erreur : opérandes invalides");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
