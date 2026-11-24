package jms;

import org.apache.activemq.broker.BrokerService;

public class EmbededActiveMQ {
    public static void main(String[] args) {
        try {
            BrokerService broker = new BrokerService();
            broker.setPersistent(false);
            broker.addConnector("tcp://0.0.0.0:61616");
            broker.start();
            System.out.println("Broker ActiveMQ démarré sur tcp://0.0.0.0:61616");
            broker.waitUntilStopped();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
