# TP2-DAR2026 — Échange de chaînes de caractères et d'objets via Sockets TCP

**Étudiant :** Bilel Abid

## Activité 2-1 : Échange de chaînes de caractères (CC)

- Utilisation des flux de caractères `BufferedReader` et `PrintWriter` pour échanger une opération complète (ex. `55 * 25`).
- Le client valide la syntaxe avant l'envoi afin de rejeter les expressions incorrectes (comme `25 ** 55` ou `abc + 3`).
- Le serveur évalue l'opération (`+`, `-`, `*`, `/`) et renvoie le résultat au client.

## Activité 2-2 : Échange d'objets sérialisés

- Création de la classe `Operation` implémentant `Serializable` pour encapsuler les deux opérandes, l'opérateur et le résultat.
- Le client envoie un objet `Operation` via `ObjectOutputStream` ; le serveur le lit via `ObjectInputStream`, effectue le calcul et renvoie l'objet contenant le résultat au client.
