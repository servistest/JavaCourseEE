package edu.jms.receiver;

import edu.jms.converter.PersonMessageConverter;
import edu.jms.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Admin on 07.10.2016.
 */
public class PersonMessageListener implements MessageListener {
    private static final Logger log= LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private JmsTemplate jmsTemplate;


    @Override
    public void onMessage(Message message) {
        PersonMessageConverter personMessageConverter=new PersonMessageConverter();
        try {
            Person person=(Person) personMessageConverter.fromMessage(message);
            log.info("Receive Person = {}",person);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
