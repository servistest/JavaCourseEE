package edu.restfull;

import edu.restfull.model.Contact;
import edu.restfull.service.controller.ContactService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringRest {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringRest.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("context.xml");
        ContactService contactService = applicationContext.getBean("contactService",ContactService.class);

//        insertContacts(contactService);
        log.debug("Count all contacts = {}",contactService.countAll());
    }
    public static void insertContacts(ContactService contactService){

        Contact contact=new Contact();
        contact.setFirstName("Alex");
        contact.setLastName("Rogov");
        contact.setBirthDate(new DateTime());
        contactService.save(contact);
        log.debug("Insert new contact with id {}  ",contact.getId());
//        Contact contact=new Contact();
//        for (int i = 61; i < 90; i++) {
//
//            contact.setFirstName("Alex"+i);
//            contact.setLastName("Rogov"+i);
//            contact.setBirthDate(new DateTime());
//            contactService.save(contact);
//            log.debug("Insert new contact with id {}  ",contact.getId());
//        }

    }
}
