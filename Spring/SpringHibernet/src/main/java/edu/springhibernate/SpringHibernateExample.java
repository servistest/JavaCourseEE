package edu.springhibernate;

import edu.springhibernate.dao.ContactDao;
import edu.springhibernate.entity.Contact;
import edu.springhibernate.entity.ContactTelDetail;
import edu.springhibernate.entity.Hobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 13.09.2016.
 */
public class SpringHibernateExample {
    private static final Logger log= LoggerFactory.getLogger(SpringHibernateExample.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("app-context-annotation.xml");
        ContactDao contactDao=applicationContext.getBean("contactDao",ContactDao.class);

//        listContactsWithDetail(contactDao.findAll());
//        listContactsWithDetail(contactDao.findAllWithDetail());
//        log.info("Contact with id =2, {}",contactDao.findById(2L));
//        insert(contactDao);
//        update(contactDao);
          delete(contactDao);
}

    private static void delete(ContactDao contactDao){
        Contact contact =contactDao.findById(10L);
        contactDao.delete(contact);
    }



    private static void update(ContactDao contactDao){
        Contact contact=contactDao.findById(11L);
        contact.setFirstName("New Name ");
        Set <ContactTelDetail> contactTelDetails=contact.getContactTelDetails();
        for (ContactTelDetail contactTelDetail:contactTelDetails){
            if (contactTelDetail.getTel_type().equals("Home")){
                contactTelDetails.remove(contactTelDetail);
            }
        }
        contact.setContactTelDetails(contactTelDetails);
        contactDao.save(contact);

    }

    private static void insert(ContactDao contactDao){
        Contact contact=new Contact();
        contact.setFirstName("Roman55");
        contact.setLastName("Rokosocskij55");
        contact.setBirthDate(new Date());
        ContactTelDetail contactTelDetail=new ContactTelDetail("Home","5555556666");
        contact.addContactTelDetails(contactTelDetail);
        contactTelDetail=new ContactTelDetail("Mobile","565656565665");
        contact.addContactTelDetails(contactTelDetail);

        Hobby hobby=new Hobby();
        hobby.setHobbyId("Bouling");
        Set<Hobby> hobbies=new HashSet<Hobby>();
        hobbies.add(hobby);
        hobby=new Hobby();
        hobby.setHobbyId("Reader");
        hobbies.add(hobby);

        contact.setHobbies(hobbies);
        contactDao.save(contact);
        log.info("Insert new Contact with id={}",contact.getId());
        listContactsWithDetail(contactDao.findAllWithDetail());
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
