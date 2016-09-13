package edu.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 06.09.2016.
 */
public class TestJdbcContactDao {
    private static final Logger log= LoggerFactory.getLogger(TestJdbcContactDao.class);

    public static void main(String[] args) {

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("datasource-drivermanager.xml");
        JdbcContactDao jdbcContactDao=applicationContext.getBean(JdbcContactDao.class);

        log.info("First name = {}",jdbcContactDao.findFirstNameById(1L));
        log.info("Last name = {}",jdbcContactDao.findLastNameById(1L));
        log.info("List all contacts  = {}",jdbcContactDao.findAll());
        log.info("List all contacts with first name Cris  = {}",jdbcContactDao.findByFirstName("Chris"));
        log.info("List all contacts with details  = {}",jdbcContactDao.findAllWithDetail());
        log.info("Select First Name By ID with Stored Function = {}",jdbcContactDao.findFirstNameByIdFunction(3L));

    }

}
