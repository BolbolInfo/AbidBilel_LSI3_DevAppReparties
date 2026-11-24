package activite2_2;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Je suis un serveur en attente la connexion d'un client ");
            try (Socket socket = serverSocket.accept()) {
                System.out.println("un client est connecté");

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.flush();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

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

                        oos.writeObject(op);
                        oos.flush();
                    } catch (EOFException e) {
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
