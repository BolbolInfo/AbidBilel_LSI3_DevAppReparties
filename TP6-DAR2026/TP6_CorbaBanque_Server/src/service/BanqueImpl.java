package service;

import java.util.HashMap;
import java.util.Map;
import corbaBanque.Compte;
import corbaBanque.IBanqueRemotePOA;

public class BanqueImpl extends IBanqueRemotePOA {
    private static final double TAUX_EURO_DINAR = 3.3;
    private final Map<Integer, Compte> comptes = new HashMap<>();

    @Override
    public synchronized void creerCompte(Compte cpte) {
        if (!comptes.containsKey(cpte.code)) {
            comptes.put(cpte.code, new Compte(cpte.code, cpte.solde));
        }
    }

    @Override
    public synchronized void verser(float mt, int code) {
        Compte c = comptes.get(code);
        if (c != null && mt > 0) {
            c.solde += mt;
        }
    }

    @Override
    public synchronized void retirer(float mt, int code) {
        Compte c = comptes.get(code);
        if (c != null && mt > 0 && c.solde >= mt) {
            c.solde -= mt;
        }
    }

    @Override
    public synchronized Compte getCompte(int code) {
        Compte c = comptes.get(code);
        if (c == null) {
            return new Compte(-1, 0.0f);
        }
        return c;
    }

    @Override
    public synchronized Compte[] getComptes() {
        return comptes.values().toArray(new Compte[0]);
    }

    @Override
    public double conversion(float mt) {
        return mt * TAUX_EURO_DINAR;
    }
}
