package rmiClient;

import java.rmi.Naming;
import rmiService.IConversion;

public class ConversionClient {
    public static void main(String[] args) {
        try {
            IConversion stub = (IConversion) Naming.lookup("rmi://localhost:1099/ConversionService");
            double montant = 500;
            double resultat = stub.convertirMontant(montant);
            System.out.println(montant + " EUR = " + resultat + " TND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
