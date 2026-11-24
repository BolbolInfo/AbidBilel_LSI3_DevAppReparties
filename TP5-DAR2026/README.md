# TP5-DAR2026 — Invocation de méthodes distantes avec Java RMI et JNDI

**Étudiant :** Bilel Abid

## Activité 5-1 : Service de conversion de devise

- Définition de l'interface distante `IConversion` (`convertirMontant(double mt)`) et de son implémentation `ConversionImpl` héritant de `UnicastRemoteObject`.
- `ConversionServer` démarre l'annuaire RMI sur le port `1099` et publie l'objet distant.
- `ConversionClient` recherche la référence distante dans le registre RMI et invoque `convertirMontant(500)`.

## Activité 5-2 : Service de banque avec RMI et JNDI

- Création de la classe sérialisable `Compte` (`code`, `solde`, `dateCreation`), de l'interface distante `IBanque` (`creerCompte`, `getInfoCompte`) et de son implémentation `BanqueImpl`.
- Enregistrement (`rebind`) et recherche (`lookup`) du service distant via JNDI (`InitialContext` configuré par `jndi.properties` pointant vers `rmi://localhost:1099`).
