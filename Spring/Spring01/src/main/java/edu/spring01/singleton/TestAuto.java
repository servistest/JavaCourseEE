package edu.spring01.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zul.A;

/**
 * Created by Admin on 25.08.2016.
 */

public class TestAuto {
    public static final Logger log= LoggerFactory.getLogger(TestAuto.class);


    public static void main(String[] args) {
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("springSingleton.xml");
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(CaseAutoConfig.class);
        Object object=applicationContext.getBean(Auto.class);

        if(object instanceof Auto ){
            Auto auto=(Auto) object;
            log.info("AUTO - {}", auto);
        }
        CaseAutoConfig caseAutoConfig=(CaseAutoConfig) applicationContext.getBean(CaseAutoConfig.class);
        log.info("CaseAutoConfig - {}",caseAutoConfig);

        Moto moto=applicationContext.getBean(Moto.class);
        log.info("Moto - {}", moto);

        ((ConfigurableApplicationContext)applicationContext).close();
    }
}
