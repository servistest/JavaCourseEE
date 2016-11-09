package edu.springtest.dao;

import edu.springtest.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Admin on 19.09.2016.
 */

public interface ContactRepository extends CrudRepository<Contact,Long> {
//    Contact findByFirstNameAndLastName(String firstName, String lastName);
//    Contact findByFirstName(String firstName);

}
