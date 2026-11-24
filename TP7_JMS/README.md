# TP7_JMS — Système distribué asynchrone avec JMS et ActiveMQ

**Étudiant :** Bilel Abid

## Description

- Projet Maven configuré avec `activemq-client`, `activemq-broker` (`5.19.1`) et `slf4j-simple` (`2.0.9`).
- `EmbededActiveMQ` démarre un broker ActiveMQ embarqué écoutant en TCP sur `0.0.0.0:61616`.
- `JMSProducer` se connecte au broker, ouvre une session transactionnelle (`AUTO_ACKNOWLEDGE`), crée un `Topic` (`myTopic.topic`) et publie un `TextMessage` non persistant.
- `JMSConsumer` s'abonne au même `Topic` et reçoit les messages de manière asynchrone via un `MessageListener`.

## Ordre d'exécution

1. Lancer `jms.EmbededActiveMQ`
2. Lancer `jms.JMSConsumer`
3. Lancer `jms.JMSProducer`
