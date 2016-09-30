package edu.validation;


import edu.validation.model.AnotherContact;
import edu.validation.model.Contact;
import edu.validation.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;


import java.util.*;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringValidation {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringValidation.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-validation.xml");
        Contact chris=applicationContext.getBean("chris",Contact.class);
        log.info("Convert String to Date {} ", chris.getBirthDate());

        ConversionService conversionService=applicationContext.getBean("conversionService",ConversionService.class);
        AnotherContact anotherContact=conversionService.convert(chris,AnotherContact.class);
        log.info("Contact convert to Another Contact {}",anotherContact);

        String[] arrayString=conversionService.convert("a,b,c",String[].class);

        for (String s :arrayString){
            log.info("Convert String  to Array[String] {}",s);
        }


        List<String> listString = new ArrayList<String>();
        listString.add("a");
        listString.add("b");
        listString.add("c");
        Set<String> setString = conversionService.convert(listString, HashSet.class);
        log.info("Convert List to Set  {}->{}",listString,setString);


    }








}
