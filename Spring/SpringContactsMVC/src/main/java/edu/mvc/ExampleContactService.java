package edu.mvc;

import edu.mvc.model.Contact;
import edu.mvc.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ALex on 13.11.2016.
 */
public class ExampleContactService {
private static final Logger log= LoggerFactory.getLogger(ExampleContactService.class);
   public ContactService contactService;

    public static void testData(ContactService contactService){
        Contact contact=contactService.findContactById(1L);
        log.info("Return contact by id  {}",contact);
        Contact contact2=contactService.findContactById(1L);
        log.info("Return contact by id  {}",contact2);
        List<Map<String, Object>> firstAndLastName=  contactService.findFirstNameAndLastNameById(1L);
        System.out.println("");
        log.info("Return contact by id={},First Name={}, Last Name={} ",1L,firstAndLastName.get(0).get("first_name"),firstAndLastName.get(0).get("last_name"));
        String firstName=contactService.findFirstNameById(1L);
        log.info("Return by id={} First Name ={}",1L,firstName);
        List<Contact> contacts=contactService.findAllContacts();
        log.info("Return all contacts {}",contacts);
        Contact contact4=new Contact();
        contact4.setFirstName("Vladimir23");
        contact4.setLastName("Novik23");
        contact4.setBirthDate(new Date());
        log.info("Save contact with id ={}",contactService.save(contact4));

        Contact contact5=new Contact();
        contact5.setFirstName("Vladimir24");
        contact5.setLastName("Novik24");
        log.info("Save contact with id ={}",contactService.save(contact5));
        contact5.setLastName("Novik24");
        contactService.delete(1L);
        log.info("Delete contact with id ={}",1L);

        contact5=contactService.findContactById(3L);
        contact5.setFirstName("New First Name");
        contact5.setDescription("About contact");
        contactService.update(contact5);
        log.info("Update contact= {} ",contact5);

    }

    public static void main(String[] args) {

        Constructor[] constructor=String.class.getDeclaredConstructors();
        log.debug("constructor = {}",constructor);
        System.out.println();
        Date date=new Date();
        Class cls=date.getClass();
        System.out.println("Class of date = "+cls);
        try {
            Object o=cls.newInstance();
            System.out.println("Instance object o = "+o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("return class = "+  String.class.getClass() );
            Object o=String.class.getClass().newInstance();
            System.out.println("String o = "+o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//       Object o= Question.TypeUser.Admin;
//        Question.TypeUser type=Question.TypeUser
//
//        System.out.println("object = "+o);




//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/data-source-postgres-tx.xml");
//        ContactService contactService=(ContactService) applicationContext.getBean("contactService");
//        testData(contactService);

//        @Bean
//        public DataSource getDataSource() {
//            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//            DataSource dataSource = dataSourceLookup.getDataSource(this.jndiName);
//            return dataSource;
//        }
//        @Bean
//        DataSource dataSource() {
//            DataSource dataSource = null;
//            JndiTemplate jndi = new JndiTemplate();
//            try {
//                dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/yourname");
//            } catch (NamingException e) {
//                logger.error("NamingException for java:comp/env/jdbc/yourname", e);
//            }
//            return dataSource;
//        }
//
    }

}
