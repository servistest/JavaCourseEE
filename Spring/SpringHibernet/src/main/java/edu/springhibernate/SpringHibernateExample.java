package edu.springhibernate;

import edu.springhibernate.dao.ContactDao;
import edu.springhibernate.entity.Contact;
import edu.springhibernate.entity.ContactTelDetail;
import edu.springhibernate.entity.Hobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Admin on 13.09.2016.
 */
public class SpringHibernateExample {
    private static final Logger log= LoggerFactory.getLogger(SpringHibernateExample.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("app-context-annotation.xml");
        ContactDao contactDao=applicationContext.getBean("contactDao",ContactDao.class);

//        listContactsWithDetail(contactDao.findAll());
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
