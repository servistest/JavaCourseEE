package edu.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * Created by Admin on 06.10.2016.
 */
public class SimpleMessageListener implements MessageListener {

    private static final Logger log= LoggerFactory.getLogger(MessageListener.class);

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage=(TextMessage) message;
        try {
            log.info("Receiver message = {}",textMessage.getText());
        } catch (JMSException e) {
            log.error("Receiver error {}",e);
        }

    }
}
