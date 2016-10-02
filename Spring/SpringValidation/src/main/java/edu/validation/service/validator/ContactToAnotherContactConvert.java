package edu.validation.service.validator;

import edu.validation.model.AnotherContact;
import edu.validation.model.Contact;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Admin on 30.09.2016.
 */
public class ContactToAnotherContactConvert implements Converter<Contact,AnotherContact> {

    @Override
    public AnotherContact convert(Contact contact) {
        AnotherContact anotherContact=new AnotherContact();
        anotherContact.setFirstName(contact.getLastName());
        anotherContact.setLastName(contact.getFirstName());
        anotherContact.setBirthDate(contact.getBirthDate());
        anotherContact.setPersonalSite(contact.getPersonalSite());
        return anotherContact;
    }
}
