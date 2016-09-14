package edu.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Admin on 08.09.2016.
 */
public class TestJdbcAnnotationContactDao {
    private static final Logger log= LoggerFactory.getLogger(TestJdbcContactDao.class);

    public static void insertContactWithDetail(ContactDao contactDao){
        log.info("=========================INSERT CONTACT WITH DETAIL========================");
        Contact contact=new Contact();
        contact.setId(1L);
        contact.setFirstName("Alekseev5");
        contact.setLastName("Yurij5");
        contact.setBirthDate(new Date(
                (new GregorianCalendar(1993,02,01).getTime()).getTime()));

        List<ContactTelDetail> listContactTelDetails=new ArrayList<>();
        ContactTelDetail contactTelDetail=new ContactTelDetail();
        contactTelDetail.setTelType("Home5");
        contactTelDetail.setTelNumber("555555555-555555555");
        listContactTelDetails.add(contactTelDetail);

        contactTelDetail=new ContactTelDetail();
        contactTelDetail.setTelType("WORK5");
        contactTelDetail.setTelNumber("55555-55555");
        listContactTelDetails.add(contactTelDetail);

        contact.setContactTelDetails(listContactTelDetails);
        contactDao.insertWithDetail(contact);

        log.info("List of contacts = {}",contactDao.findAllWithDetail());

    }
    public static void updateContact(ContactDao contactDao){
        log.info("=========================UPDATE CONTACT========================");
        Contact contact=new Contact();
        contact.setId(1L);
        contact.setFirstName("Egor2");
        contact.setLastName("Serov2");
        contact.setBirthDate(new Date(
                (new GregorianCalendar(1995,12,02).getTime()).getTime()));

        log.info("UPDATE Contact = {}",contactDao.findFirstNameById(1L));
        contactDao.update(contact);
        log.info("On Contact = {}",contact);
    }

    public static void insertContact(ContactDao contactDao){
        log.info("=========================INSERT CONTACT========================");
        Contact contact=new Contact();
        contact.setId(1L);
        contact.setFirstName("Andrey");
        contact.setLastName("Rebrov");
        contact.setBirthDate(new Date(new GregorianCalendar(1981,05,03).getTime().getTime()));
        contactDao.insert(contact);
        log.info("Insert Contact {}" , contact);
        log.info("List of contacts = {}",contactDao.findAll());
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("datasource-contactdao-annotation.xml");
//        JdbcContactDaoAnnotation contactDao=applicationContext.getBean("contactDao",JdbcContactDaoAnnotation.class);
        ContactDao contactDao=applicationContext.getBean("contactDao",ContactDao.class);
        log.info("=========================SELECT ALL  CONTACT========================");
        log.info("List of contacts = {}",contactDao.findAll());
        log.info("=========================SELECT  CONTACT BY FIRST NAME========================");
        log.info("List of contacts = {}",contactDao.findByFirstName("John"));
        log.info("=========================SELECT  FIRST NAME BY ID========================");
        log.info("Select  on id = {}  first name= {}" ,1 , contactDao.findFirstNameById(1L) );
        log.info("=========================SELECT  LAST NAME BY ID========================");
        log.info("Select  on id = {}  last name = {}" ,1 , contactDao.findFirstNameById(1L) );
//        insertContact(contactDao);
//        updateContact(contactDao);
//        insertContactWithDetail(contactDao);


    }

}
