package activite3_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProcess extends Thread {
    private final Socket socket;
    private final int numeroClient;

    public ClientProcess(Socket socket, int numeroClient) {
        this.socket = socket;
        this.numeroClient = numeroClient;
    }

    @Override
    public void run() {
        try (Socket s = this.socket;
             BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true)) {

            pw.println("Bienvenue ! Vous êtes le client n°" + numeroClient);

            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (ligne.equalsIgnoreCase("exit")) {
                    break;
                }

                String[] parts = ligne.trim().split("\\s+");
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
            System.out.println("Client n°" + numeroClient + " déconnecté.");
        } catch (IOException e) {
            System.out.println("Client n°" + numeroClient + " déconnecté.");
        }
    }
}
