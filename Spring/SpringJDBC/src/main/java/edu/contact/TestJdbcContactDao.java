package edu.contact;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 06.09.2016.
 */
public class TestJdbcContactDao {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("datasource-drivermanager.xml");
        JdbcContactDao jdbcContactDao=applicationContext.getBean(JdbcContactDao.class);
        jdbcContactDao.findAll();
        jdbcContactDao.findFirstNameById(1L);
    }

}
