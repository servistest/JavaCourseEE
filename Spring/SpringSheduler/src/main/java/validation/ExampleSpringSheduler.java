package validation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringSheduler {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringSheduler.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-validation.xml");
        while (true){}


    }








}
