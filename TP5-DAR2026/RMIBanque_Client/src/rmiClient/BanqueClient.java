package rmiClient;

import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import metier.Compte;
import rmiService.IBanque;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();
            IBanque stub = (IBanque) ctx.lookup("BanqueService");

            System.out.println(stub.creerCompte(new Compte(101, 1500.0, new Date())));
            System.out.println(stub.creerCompte(new Compte(102, 2750.5, new Date())));
            System.out.println(stub.creerCompte(new Compte(101, 500.0, new Date())));

            System.out.println(stub.getInfoCompte(101));
            System.out.println(stub.getInfoCompte(102));
            System.out.println(stub.getInfoCompte(999));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
