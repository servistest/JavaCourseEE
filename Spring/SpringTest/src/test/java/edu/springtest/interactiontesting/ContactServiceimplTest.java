package edu.springtest.interactiontesting;

import edu.springtest.model.Contact;
import edu.springtest.service.controller.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

/**
 * Created by Admin on 08.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceTestConfig.class)
@TestExecutionListeners(ServiceTestExecutionListener.class)
@ActiveProfiles("test")
//   !!!!!   For run test - change in Contact.java  @Table(name = "restfull.contact")  on @Table(name = "contact")!!!!
public class ContactServiceImplTest extends AbstractJUnit4SpringContextTests {
    private static Logger log= LoggerFactory.getLogger(ContactServiceImplTest.class);
    @Autowired
    ContactService contactService;
    @PersistenceContext
    EntityManager emf;
    @Autowired
    DataSource dataSource;

    @DataSets(setUpDataSet = "/xls/springtest/service/controller/ContactServiceImplTest.xls")
    @Test
    public void testFindAll() throws Exception{
        List<Contact> contactList=contactService.findAll();
        assertNotNull(contactList);
        assertEquals(1,contactList.size());
    }

    @Test
    @DataSets(setUpDataSet = "/xls/springtest/service/controller/ContactServiceImplTest.xls")
    public void testFindByFirstName() throws Exception{
        Contact contact=contactService.findByFirstName("Chris");
        assertNotNull(contact);
        assertEquals("Chris",contact.getFirstName());
    }

    @Test
    @DataSets(setUpDataSet = "/xls/springtest/service/controller/ContactServiceImplTest.xls")
    public void testFindByFirstAndLastName_1() throws Exception{
//        Contact contact=contactService.findByFirstNameAndLastName("Chris","Schaefer");
//        assertNotNull(contact);
    }

    @Test
    @DataSets(setUpDataSet = "/xls/springtest/service/controller/ContactServiceImplTest.xls")
    public void testFindByFirstAndLastName_2() throws Exception{

        Contact contact=contactService.findByFirstNameAndLastName("Chris","Schaef");
        assertNull(contact);
    }

    @Test
    public void saveVariousContactShouldByReturnTrue() throws Exception{
//        deleteFromTables(jdbcTemplate,"CONTACT");
        Contact contact=new Contact();
        contact.setFirstName("Rod");
        contact.setLastName("Johnson");
        contactService.save(contact);

//        emf.flush();

        List<Contact> contactList=contactService.findAll();
        log.info("Contact list ={}",contactList);
        assertEquals(1,contactList.size());
    }

    @Test(expected = Exception.class)
    public void saveVariousContactShouldByReturnError() throws Exception{


//        deleteFromTables(jdbcTemplate,"CONTACT");
        Contact contact=new Contact();
        contactService.save(contact);

        emf.flush();

        List<Contact> contactList=contactService.findAll();
        log.info("Contact list ={}",contactList);
        assertEquals(0,contactList.size());
    }


}
