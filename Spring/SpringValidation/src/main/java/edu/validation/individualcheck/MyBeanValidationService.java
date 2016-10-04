package edu.validation.individualcheck;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by ALex on 02.10.2016.
 */
@Service(value = "myBeanValidationService")
public class MyBeanValidationService {
    @Autowired
    public Validator validator;

    public Set<ConstraintViolation<CustomerAssert>> validationCustomer(CustomerAssert customer){
        return validator.validate(customer);
    }

}
