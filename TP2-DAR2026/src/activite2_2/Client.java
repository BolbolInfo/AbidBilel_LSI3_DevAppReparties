package activite2_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Je suis un client pas encore connecté...");
        try (Socket socket = new Socket(HOST, PORT);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("je suis un client connecté");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.print("Premier opérande : ");
                double op1 = scanner.nextDouble();

                System.out.print("Opérateur (+, -, *, /) : ");
                String operateur = scanner.next();

                if (!operateur.equals("+") && !operateur.equals("-")
                        && !operateur.equals("*") && !operateur.equals("/")) {
                    System.out.println("Opérateur invalide.");
                    continue;
                }

                System.out.print("Deuxième opérande : ");
                double op2 = scanner.nextDouble();

                Operation operation = new Operation(op1, operateur, op2);
                oos.writeObject(operation);
                oos.flush();

                Operation reponse = (Operation) ois.readObject();
                if (reponse.getErreur() != null) {
                    System.out.println("Erreur : " + reponse.getErreur());
                } else {
                    System.out.println("Résultat = " + reponse.getResultat());
                }

                System.out.print("Continuer ? (o/n) : ");
                String suite = scanner.next();
                if (!suite.equalsIgnoreCase("o")) {
                    oos.writeObject(null);
                    oos.flush();
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
