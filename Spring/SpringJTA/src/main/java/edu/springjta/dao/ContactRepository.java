package edu.springjta.dao;

import edu.springjta.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Admin on 19.09.2016.
 */

public interface ContactRepository extends CrudRepository<Contact,Long> {
    @Query("select count (c) from Contact c")
    Long countAllContacts();

}