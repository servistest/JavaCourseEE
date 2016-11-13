package edu.mvc.service;

import edu.mvc.model.Contact;

import java.util.List;

/**
 * Created by ALex on 13.11.2016.
 */
public interface ContactService {
    void findById (Long id);
    void save (Contact contact);
    List<Contact> findAll ();

}
