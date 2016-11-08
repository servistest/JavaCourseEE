package edu.springtest;


import edu.springtest.model.Contact;
import edu.springtest.model.Contacts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringRestSecurity {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringRestSecurity.class);
    private static final String URL_GET_ALL_CONTACTS="http://localhost:8080/SpringTest/restful/contact/listData";
    private static final String URL_GET_CONTACT_BY_ID="http://localhost:8080/SpringTest/restful/contact/{id}";
    private static final String URL_CREATE_CONTACT="http://localhost:8080/SpringTest/restful/contact/";
    private static final String URL_UPDATE_CONTACT="http://localhost:8080/SpringTest/restful/contact/{id}";
    private static final String URL_DELETE_CONTACT="http://localhost:8080/SpringTest/restful/contact/{id}";


    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/rest-client-app-context.xml");

        Contacts contacts;
        log.info("Testing retrieve all contacts:");
        RestTemplate restTemplate=applicationContext.getBean("restTemplate", RestTemplate.class);
        contacts=restTemplate.getForObject(URL_GET_ALL_CONTACTS,Contacts.class);
        contactOut(contacts);

        log.info("=========Testing retrieve  contact by id:=============");
        Contact contact=restTemplate.getForObject(URL_GET_CONTACT_BY_ID,Contact.class,1);
        log.info("Get contact by id= {}",contact);

//        log.info("=========Testing update contact :=============");
//        contact.setFirstName("New Name1");
//        restTemplate.put(URL_UPDATE_CONTACT,contact,1);
//        log.info("Update contact successfully = {}",contact);
//
        log.info("=========Testing delete contact :=============");
        restTemplate.delete(URL_DELETE_CONTACT,2);
        log.info("Delete contact successfully = {}",contact);
        contacts=restTemplate.getForObject(URL_GET_ALL_CONTACTS,Contacts.class);
        contactOut(contacts);
//
//        log.info("=========Testing insert contact :=============");
//        Contact insertContact= new Contact();
//        insertContact.setFirstName("insertAndrew");
//        insertContact.setLastName("insertBokeev");
//        insertContact.setBirthDate(new DateTime());
//        restTemplate.postForObject(URL_CREATE_CONTACT,insertContact,Contact.class);
//        log.info("Delete contact successfully = {}",insertContact);
//        contacts=restTemplate.getForObject(URL_GET_ALL_CONTACTS,Contacts.class);
//        contactOut(contacts);
    }

    public static void contactOut(Contacts contacts) {
        for (Contact contact:contacts.getContacts()){
            log.info("contact = {}" ,contact);
        }
    }
}
