package edu.spring01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 23.08.2016.
 */
public class main {
    public static void main(String[] args) {
//        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(MainConfiguration.class);
//        or
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("springExample.xml");
        A a = applicationContext.getBean(A.class);
        System.out.println("Name class = "+a.getClass().getCanonicalName()+ a.toString());
    }
}
