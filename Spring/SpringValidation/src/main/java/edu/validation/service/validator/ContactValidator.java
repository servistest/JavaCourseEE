package edu.validation.service.validator;


import edu.validation.model.Contact;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by ALex on 02.10.2016.
 */
@Component(value = "contactValidator")
public class ContactValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Contact.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"firstName","firstName.empty");
    }
}

