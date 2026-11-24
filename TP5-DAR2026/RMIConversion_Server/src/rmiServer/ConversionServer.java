package rmiServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import rmiService.ConversionImpl;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ConversionImpl od = new ConversionImpl();
            Naming.rebind("rmi://localhost:1099/ConversionService", od);
            System.out.println("Serveur RMI de conversion prêt...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
