package edu.hibernateenvers;

import edu.hibernateenvers.dao.ContactAuditService;
import edu.hibernateenvers.model.ContactAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    public static void insertContactAudit(ContactAuditService contactAuditService){
        ContactAudit contactAudit=contactAuditService.findById(1L);
        log.info("Contact with id = {} ",contactAudit);
        contactAudit.setFirstName("AndrewUpdate2");
        contactAudit.setLastName("SokolovUpdate2");
        contactAuditService.save(contactAudit);
        log.info("Update contact with id 1 {}",contactAudit);

//        save new Contact:
        ContactAudit contactAudit1=new ContactAudit();
        contactAudit1.setFirstName("Andrew2");
        contactAudit1.setLastName("Egorov2");
        contactAudit1.setBirthDate(new Date());
        contactAuditService.save(contactAudit1);
        List<ContactAudit> contactAuditList=contactAuditService.findAll();
        log.info("All contact with AUDIT ={}",contactAuditList);
        //test
    }

}
