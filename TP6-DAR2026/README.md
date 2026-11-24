# TP6-DAR2026 — Service Banque avec CORBA

**Étudiant :** Bilel Abid

## Description

- Définition de l'interface distante dans `Banque.idl` (`module corbaBanque`, `struct Compte`, `typedef sequence<Compte> tabComptes`, `interface IBanqueRemote`).
- Implémentation du servant `BanqueImpl` héritant de `IBanqueRemotePOA` avec les opérations `creerCompte`, `verser`, `retirer`, `getCompte`, `getComptes` et `conversion` (Euro vers Dinar Tunisien).
- Initialisation de l'ORB, du `RootPOA` et publication de l'objet distant via JNDI (`iiop://localhost:900`) dans `BanqueServer`.
- Recherche JNDI, `narrow` via `IBanqueRemoteHelper` et appels distants des méthodes bancaires depuis `BanqueClient`.

## Exécution (JDK 8)

1. Compiler `Banque.idl` dans le répertoire `src` de chaque projet : `idlj -fall -v Banque.idl`
2. Lancer l'annuaire de noms CORBA : `tnameserv`
3. Lancer `corbaServer.BanqueServer` puis `corbaClient.BanqueClient`
