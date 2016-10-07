package edu.jms.send;

import edu.jms.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Admin on 06.10.2016.
 */
@Component("messageSender")
public class SimpleMessageSender implements MessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void SendMessage(final String message) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage(message);
            }
        });
    }

    @Override
    public void SendPerson(Person person) {
        System.out.println("Send Message = "+person.toString());
        jmsTemplate.convertAndSend(person);
    }
}
