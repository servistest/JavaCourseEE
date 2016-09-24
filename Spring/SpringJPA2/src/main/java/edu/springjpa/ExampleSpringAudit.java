package edu.springjpa;

import edu.springjpa.dao.ContactAuditService;
import edu.springjpa.model.ContactAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 19.09.2016.
 */
public class ExampleSpringAudit {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringAudit.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-audit-annotation.xml");
        ContactAuditService contactAuditService=applicationContext.getBean("contactAuditService",ContactAuditService.class);
        exampleAudit(contactAuditService);
    }
    public static void exampleAudit(ContactAuditService contactAuditService ){
        ContactAudit contactAudit=contactAuditService.findById(1L);
        log.info("Contact with id = {} ",contactAudit);
        contactAudit.setFirstName("AndrewUpdate2");
        contactAudit.setLastName("SokolovUpdate2");
        contactAuditService.save(contactAudit);
        log.info("Update contact with id 1 {}",contactAudit);

//        save new Contact:
//        ContactAudit contactAudit1=new ContactAudit();
//        contactAudit1.setFirstName("Andrew7");
//        contactAudit1.setLastName("Egorov7");
//        contactAudit1.setBirthDate(new Date());
//        contactAuditService.save(contactAudit1);
        List<ContactAudit> contactAuditList=contactAuditService.findAll();
        log.info("All contact with AUDIT ={}",contactAuditList);

        log.info("Find Contact by  name Andrew4 : {} ",contactAuditService.findByFirstName("Andrew4"));
        log.info("Find Contact by  name Andrew6 Egorov6   : {} ",contactAuditService.findByFirstAndLastName("Andrew6","Egorov6"));

        log.info("Find Contact with JPA @Query  by  name Andrew4 : {} ",contactAuditService.findByMyName("Andrew4"));
        log.info("Find Contact with JPA @Query like  by  name Andrew* : {} ",contactAuditService.findByMyNameLike("Andrew"));
//        Page<ContactAudit> contactAuditPage=);
        log.info("Find Contact with JPA @Query with Pagegable by  last name Egorov6 : {} ",contactAuditService.findByLastName("Egorov6", new PageRequest(1,20)));

        log.info("Find Contact with JPA @Query with like *ov* and sort by  last name  : {} ",contactAuditService.findByNameAndSort("ov",new Sort("firstName")));
        log.info("Find Contact with JPA @Query with @Param : lastName or firstName   : {} ",contactAuditService.findByFirstNameOrLastName("Andrew5","Egorov6"));

    }
}
