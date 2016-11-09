package edu.springtest.service.controller;


import edu.springtest.model.Contact;

import java.util.List;

/**
 * Created by Admin on 13.09.2016.
 */

public interface ContactService {
    List findAll();
    Contact findById(Long id);
    Contact findByFirstNameAndLastName(String firstName,String lastName);
    Contact findByFirstName(String firstName);
    Contact save(Contact contact);
    void delete(Contact contact);
    Long countAll();
}
