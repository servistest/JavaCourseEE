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
import java.sql.ResultSet;
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


    }
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/data-source-postgres-tx.xml");
        ContactService contactService=(ContactService) applicationContext.getBean("contactService");
        testData(contactService);

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