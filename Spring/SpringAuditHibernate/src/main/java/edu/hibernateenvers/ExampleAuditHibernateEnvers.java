package edu.hibernateenvers;

import edu.hibernateenvers.dao.ContactAuditService;
import edu.hibernateenvers.model.ContactAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ALex on 24.09.2016.
 */
public class ExampleAuditHibernateEnvers {
    private static final Logger log= LoggerFactory.getLogger(ExampleAuditHibernateEnvers.class);


    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-audit-hibernate-envers.xml");
        ContactAuditService contactAuditService=applicationContext.getBean("contactAuditService",ContactAuditService.class);
        insertContactAudit(contactAuditService);
    }
    @Transactional
    public static void insertContactAudit(ContactAuditService contactAuditService){
//        ContactAudit contactAudit=contactAuditService.findById(2L);
//        log.info("Contact with id = {} ",contactAudit);
//        contactAudit.setFirstName("AndrewUpdate4");
//        contactAudit.setLastName("SokolovUpdate4");
//        contactAuditService.save(contactAudit);
//        log.info("Update contact with id 1 {}",contactAudit);

//        save new Contact:
        ContactAudit contactAudit1=new ContactAudit();
        for (int i=1;i<=10;i++){
            String firstName = "Andrew".concat(Integer.toString(i));
            contactAudit1.setFirstName(firstName);
            String lastName = "Egorov".concat(Integer.toString(i));
            contactAudit1.setLastName(lastName);
            contactAudit1.setBirthDate(new Date());
            contactAuditService.save(contactAudit1);

        }
        log.info("Contact with with id=4 ={} and revision version=1 {}",contactAuditService.findById(4L),contactAuditService.findAuditByRevision(4L,118));

        List<ContactAudit> contactAuditList=contactAuditService.findAll();
        log.info("All contact with AUDIT ={}",contactAuditList);
        //test
    }

}
