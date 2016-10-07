package edu.jms.send;

import edu.jms.model.Person;

/**
 * Created by Admin on 06.10.2016.
 */
public interface MessageSender {
    void SendMessage(String message);
    void SendPerson(Person person);
}
