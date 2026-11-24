package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import metier.Compte;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {
    private static final long serialVersionUID = 1L;
    private final Map<Integer, Compte> comptes = new HashMap<>();

    public BanqueImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized String creerCompte(Compte c) throws RemoteException {
        if (comptes.containsKey(c.getCode())) {
            return "Erreur : un compte avec le code " + c.getCode() + " existe déjà.";
        }
        comptes.put(c.getCode(), c);
        return "Compte créé avec succès : " + c.toString();
    }

    @Override
    public synchronized String getInfoCompte(int code) throws RemoteException {
        Compte c = comptes.get(code);
        if (c == null) {
            return "Aucun compte trouvé pour le code " + code + ".";
        }
        return c.toString();
    }
}
