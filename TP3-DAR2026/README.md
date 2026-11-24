# TP3-DAR2026 — Serveur Multi-threads avec Sockets TCP

**Étudiant :** Bilel Abid

## Activité 3-1 : Acceptation de plusieurs clients

- Le serveur crée un thread `ClientProcess` pour chaque client connecté, affiche son adresse IP via `getRemoteSocketAddress()` ainsi que son numéro d'ordre, et lui renvoie ce numéro.
- Le client utilise `InetAddress` et `InetSocketAddress` pour se connecter en local (`127.0.0.1`) ou sur un réseau local, et le serveur est également testable avec `telnet localhost 1234`.

## Activité 3-2 : Service de calculatrice multi-thread et synchronisation

- Le serveur multi-thread traite en parallèle des objets `Operation` (`Serializable`) échangés via `ObjectInputStream` et `ObjectOutputStream`.
- Un compteur global partagé (`compteurOperations`) est protégé par une méthode `synchronized` afin de garantir la cohérence du nombre total d'opérations traitées en accès concurrent.
