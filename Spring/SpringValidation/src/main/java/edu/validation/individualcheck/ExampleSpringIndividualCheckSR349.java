package edu.validation.individualcheck;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 15.09.2016.
 */
public class ExampleSpringIndividualCheckSR349 {
    private static final Logger log= LoggerFactory.getLogger(ExampleSpringIndividualCheckSR349.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring-validation.xml");

        validation(applicationContext);

    }
    public static void validation(ApplicationContext applicationContext){
        MyBeanValidationService myBeanValidationService=applicationContext.getBean("myBeanValidationService",MyBeanValidationService.class);
        CustomerAssert customer=new CustomerAssert();
        customer.setFirstName("S");
        customer.setLastName(null);
        customer.setGender(null);
        customer.setCustomerType(CustomerType.INDIVIDUAL);
        validationCustomer(customer,myBeanValidationService);

    }
    public static void validationCustomer(CustomerAssert customer,MyBeanValidationService myBeanValidationService){
        Set<ConstraintViolation<CustomerAssert>> violations=new HashSet();
        violations=myBeanValidationService.validationCustomer(customer);
        listViolations(violations);
    }
    public static void listViolations(Set<ConstraintViolation<CustomerAssert>> violations){
        log.info(" Count error value  :  {}",violations.size());
        for (ConstraintViolation<CustomerAssert> violation:violations){
            log.info("Validation error property :  {}",violation.getPropertyPath());
            log.info(" value :  {}",violation.getInvalidValue());
            log.info(" error message :  {}",violation.getMessage());


        }

    }











}
