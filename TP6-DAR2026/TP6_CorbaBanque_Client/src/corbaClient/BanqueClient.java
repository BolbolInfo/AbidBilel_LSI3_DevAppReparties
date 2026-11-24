package corbaClient;

import javax.naming.Context;
import javax.naming.InitialContext;
import corbaBanque.Compte;
import corbaBanque.IBanqueRemote;
import corbaBanque.IBanqueRemoteHelper;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();
            Object ref = ctx.lookup("BK");
            IBanqueRemote stub = IBanqueRemoteHelper.narrow((org.omg.CORBA.Object) ref);

            stub.creerCompte(new Compte(1, 1000.0f));
            stub.creerCompte(new Compte(2, 2500.0f));

            stub.verser(500.0f, 1);
            stub.retirer(200.0f, 2);

            Compte c1 = stub.getCompte(1);
            System.out.println("Compte 1 -> code=" + c1.code + ", solde=" + c1.solde);

            System.out.println("Liste des comptes :");
            for (Compte c : stub.getComptes()) {
                System.out.println("code=" + c.code + ", solde=" + c.solde);
            }

            double dt = stub.conversion(100.0f);
            System.out.println("100 EUR = " + dt + " DT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
