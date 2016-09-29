package edu.springjta;

import edu.springjta.model.Contact;
import edu.springjta.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringProgramTransaction {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringProgramTransaction.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-program-transaction.xml");
        ContactService contactService = applicationContext.getBean("contactServiceProgramTransaction",ContactService.class);

        insertContacts(contactService);
        log.debug("Count all contacts with  program transaction = {}",contactService.countAll());
    }
    public static void insertContacts(ContactService contactService){

        Contact contact=new Contact();
        for (int i = 61; i < 90; i++) {

            contact.setFirstName("Alex"+i);
            contact.setLastName("Rogov"+i);
            contact.setBirthDate(new Date());
            contactService.save(contact);
            log.debug("Insert new contact with id {} with program transaction ",contact.getId());
        }



    }
}
