package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {
    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;
        try {
            String brokerUrl = (args.length > 0) ? args[0] : "tcp://localhost:61616";
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Topic destination = session.createTopic("myTopic.topic");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage message = session.createTextMessage("Hello World! From: Bilel Abid");
            producer.send(message);
            session.commit();

            System.out.println("Message envoyé : " + message.getText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
