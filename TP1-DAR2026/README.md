# TP1-DAR2026 — Les sockets en mode connecté TCP

**Étudiant :** Bilel Abid

## Activité 1-1 : Première connexion Client/Serveur

- Le serveur ouvre un `ServerSocket` sur le port `1234` et reste bloqué sur `accept()` en attendant un client.
- Le client se connecte via `localhost` puis via l'adresse IP `127.0.0.1`. Une fois la connexion TCP établie, les deux côtés affichent la confirmation puis ferment leurs sockets.

## Activité 1-2 : Service de calculatrice (x * 5)

- Avec `InputStream` et `OutputStream`, seuls les entiers sur 1 octet non signé (`0` à `255`) passent correctement ; les valeurs négatives ou supérieures à `255` sont tronquées et donnent un résultat erroné.
- L'utilisation de `DataInputStream` (`readInt`) et `DataOutputStream` (`writeInt`) transfère des entiers signés sur 32 bits, ce qui corrige les nombres négatifs.
- Une boucle permet d'envoyer plusieurs entiers successivement et ferme proprement la connexion lorsque l'utilisateur saisit `0`.

## Activité 1-3 : Connexion réseau et calculatrice améliorée

- `InetAddress` et `InetSocketAddress` permettent au serveur d'écouter sur son adresse IP réelle et au client de s'y connecter depuis une autre machine du réseau local.
- Le client propose un menu avec 4 opérations (`+`, `-`, `*`, `/` avec 5) et `0` pour quitter ; le serveur calcule le résultat et le renvoie au client.
