package edu.jms;

import edu.jms.send.MessageSender;
import edu.jms.send.SimpleMessageSender;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.net.URI;

/**
 * Created by Admin on 06.10.2016.
 */
public class JmsSampleSendText {
    private static final Logger log= LoggerFactory.getLogger(JmsSampleSendText.class);

    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext applicationContext= new GenericXmlApplicationContext();
        applicationContext.load("jms-listener-app-context.xml");
        applicationContext.load("jms-sender-app-context.xml");
        applicationContext.refresh();

        //Start broker
        BrokerService broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
        broker.start();

        //send message
        MessageSender messageSender=applicationContext.getBean("messageSender", SimpleMessageSender.class);
        for (int i = 0; i < 10; i++) {
            try {
                messageSender.SendMessage("Hello main friend " +i);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
