package edu.validation;


import edu.validation.model.AnotherContact;
import edu.validation.model.Contact;
import edu.validation.service.validator.ContactValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;


import java.util.*;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringValidation {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringValidation.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-validation.xml");
        convert(applicationContext);
        validation(applicationContext);

    }
    public static void validation(ApplicationContext applicationContext){
        Contact contact=new Contact();
        contact.setFirstName(null);
        contact.setLastName("Smirnov");
        ContactValidator contactValidator=applicationContext.getBean("contactValidator",ContactValidator.class);
        BeanPropertyBindingResult beanPropertyBindingResult=new BeanPropertyBindingResult(contact,"Chris");
        ValidationUtils.invokeValidator(contactValidator,contact,beanPropertyBindingResult);
        List<ObjectError> objectErrorList=beanPropertyBindingResult.getAllErrors();
        log.info("Count all errors validation = {}",objectErrorList.size());
        for (ObjectError error:objectErrorList){
            log.info("All errors validation = {}",error.getCode());
        }

//        contactValidator.validate(contact,);
    }

    public static void convert(ApplicationContext applicationContext){
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
