package edu.restsecurity.dao;


import edu.restsecurity.model.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Admin on 19.09.2016.
 */

public interface ContactRepository extends CrudRepository<Contact,Long> {

}