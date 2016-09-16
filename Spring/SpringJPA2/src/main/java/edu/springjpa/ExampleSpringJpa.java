package edu.springjpa;

import edu.springjpa.dao.ContactService;
import edu.springjpa.model.Contact;
import edu.springjpa.model.ContactTelDetail;
import edu.springjpa.model.Hobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringJpa {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringJpa.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("app-context-annotation.xml");
        ContactService contactService = applicationContext.getBean("jpaContactService",ContactService.class);
        log.debug("List all contacts{}",contactService.findAll());
        listContactsWithDetail(contactService.findAllWithDetail());
        log.debug("Select contact with id 2  = {}",contactService.findById(2L));
//        insertContact(contactService);
//        updateContact(contactService);

    }

    public static void updateContact(ContactService contactService){
        Contact contact= contactService.findById(1L);
        contact.setFirstName("New name 4");
        Set<ContactTelDetail> contactTelDetails=contact.getContactTelDetails();
        ContactTelDetail toDeleteContactTel=null;
        for (ContactTelDetail contactTelDetail:contactTelDetails){
            if (contactTelDetail.getTel_type().equals("Mobile")){
//                toDeleteContactTel=contactTelDetail;
                contactTelDetails.remove(contactTelDetail);
            }
        }
//
        contact.setContactTelDetails(contactTelDetails);
        contactService.save(contact);
    }

    public static void insertContact(ContactService contactService){
        Contact contact=new Contact();
        contact.setFirstName("Egor");
        contact.setLastName("Fedorov");
        contact.setBirthDate(new Date());
        contactService.save(contact);

    }


    private static void listContactsWithDetail(List<Contact> contacts){
        log.info("List contacts with detail:");
        for (Contact contact:contacts){
            log.info("{}",contact);
            if (contact.getContactTelDetails()!=null){
                for (ContactTelDetail contactTelDetail:contact.getContactTelDetails()){
                    log.info(" {}",contactTelDetail);
                }
            }
            if (contact.getHobbies()!=null){
                for (Hobby hobby:contact.getHobbies()){
                    log.info("{} ",hobby);
                }
            }
        }
    }
}
