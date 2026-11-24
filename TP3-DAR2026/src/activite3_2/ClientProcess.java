package activite3_2;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        try (Socket s = this.socket) {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            oos.writeObject("Bienvenue ! Vous êtes le client n°" + numeroClient);
            oos.flush();

            while (true) {
                try {
                    Operation op = (Operation) ois.readObject();
                    if (op == null) {
                        break;
                    }

                    double a = op.getOperande1();
                    double b = op.getOperande2();
                    String operateur = op.getOperateur();

                    switch (operateur) {
                        case "+":
                            op.setResultat(a + b);
                            break;
                        case "-":
                            op.setResultat(a - b);
                            break;
                        case "*":
                            op.setResultat(a * b);
                            break;
                        case "/":
                            if (b == 0) {
                                op.setErreur("Division par zéro impossible");
                            } else {
                                op.setResultat(a / b);
                            }
                            break;
                        default:
                            op.setErreur("Opérateur non supporté");
                            break;
                    }

                    int totalOperations = Server.incrementerCompteurOperations();
                    System.out.println("Client n°" + numeroClient + " : " + a + " " + operateur + " " + b
                            + " | Total opérations traitées : " + totalOperations);

                    oos.writeObject(op);
                    oos.flush();
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("Client n°" + numeroClient + " déconnecté.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client n°" + numeroClient + " déconnecté.");
        }
    }
}
