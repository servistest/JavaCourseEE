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
public class ExampleSpringJTA {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringJTA.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("context-annotation-jta.xml");
        ContactService contactService = applicationContext.getBean("contactService",ContactService.class);

        log.debug("List all contacts{}",contactService.findAll());
        Contact contact=contactService.findById(1L);
        log.debug("List contact by id 1 = {}",contact);

        contact.setFirstName("Peter");
        contactService.save(contact);
        log.debug("Contact save successfully id 1 = {}",contact);
        log.debug("Count all contacts with Named Query = {}",contactService.countAll());
    }

    public static void listsContactAudit(){

   }



    public static void insertContact(ContactService contactService){
        Contact contact=new Contact();
        contact.setFirstName("Egor");
        contact.setLastName("Fedorov");
        contact.setBirthDate(new Date());
        contactService.save(contact);

    }


}
