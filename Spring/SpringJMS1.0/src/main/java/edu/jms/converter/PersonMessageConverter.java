package edu.jms.converter;

import edu.jms.model.Person;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Admin on 07.10.2016.
 */
public class PersonMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object object, Session session) throws JMSException {
        Person person=(Person) object;
        MapMessage message=session.createMapMessage();
        message.setString("name",person.getName());
        message.setInt("age",person.getAge());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException {
        MapMessage mapMessage=(MapMessage) message;
        Person person=new Person(mapMessage.getString("name"),mapMessage.getInt("age"));
        return person;
    }
}
