# TP4-DAR2026 — Service de Chat Client/Serveur avec UDP

**Étudiant :** Bilel Abid

## Étape 2 : Chat unidirectionnel UDP

- Le serveur écoute sur le port `1234` via `DatagramSocket` et `InetSocketAddress`, puis affiche l'adresse IP, le port et le contenu de chaque datagramme reçu.
- Le client demande un nom d'utilisateur et envoie les messages préfixés sous la forme `[NomUtilisateur] : message` via `DatagramPacket`.

## Étape 3 : Chat bidirectionnel et diffusion multi-clients

- Le serveur conserve l'ensemble des adresses `SocketAddress` des clients ayant envoyé un message et rediffuse chaque message reçu à tous les autres clients enregistrés.
- Le client utilise un thread dédié à la réception afin d'envoyer et de recevoir des messages de manière asynchrone en temps réel.
