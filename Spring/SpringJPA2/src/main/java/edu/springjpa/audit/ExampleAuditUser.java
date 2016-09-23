package edu.springjpa.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 20.09.2016.
 */
public class ExampleAuditUser {
    private static final Logger log= LoggerFactory.getLogger(ExampleAuditUser.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-audit-user-foo.xml");
        CurrentUserService currentUserService=applicationContext.getBean("currentUserService",CurrentUserService.class);
        log.info("List all users : {}",currentUserService.findAllUser());

//        log.info("List  user by id = {}",currentUserService.getCurrentUser(1L));
        User user=new User();
        user.setName("Simona");
        currentUserService.save(user);
//        log.info("Save user SIMONA {} with id = {}",currentUserService.save(user).toString(),user.getId());
//        log.info("List all user = {}",currentUserService.findAllUser());
//        user.setName("Veronika");
//        log.info("Update user with id ={} ",user.getId());
//        log.info("List all user = {}",currentUserService.findAllUser());

    }
}
