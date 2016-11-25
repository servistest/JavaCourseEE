package edu.aop.aspectJAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 25.11.2016.
 */
public class AspectJAnnotationExample {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("app-context-annotation-advice.xml");
        MyBean myBean=(MyBean) applicationContext.getBean("myBean");
        myBean.execute();
    }
}
