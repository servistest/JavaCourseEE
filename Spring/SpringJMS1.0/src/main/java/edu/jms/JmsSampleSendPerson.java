package edu.jms;

import edu.jms.model.Person;
import edu.jms.receiver.PersonMessageListener;
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
public class JmsSampleSendPerson {
    private static final Logger log= LoggerFactory.getLogger(JmsSampleSendPerson.class);

    public static void main(String[] args) throws Exception {

        //Start broker
        BrokerService broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
        broker.start();
        Thread.sleep(10000);

        GenericXmlApplicationContext applicationContext= new GenericXmlApplicationContext();
        applicationContext.load("jms-message-converter-context.xml");
        applicationContext.refresh();

        //send message
        MessageSender messageSender=applicationContext.getBean("messageSender", SimpleMessageSender.class);
        Person person=new Person("Bob",32);
        for (int i = 0; i < 4; i++) {
                person.setAge(32+i);
                messageSender.SendPerson(person);
        }
    }
}
