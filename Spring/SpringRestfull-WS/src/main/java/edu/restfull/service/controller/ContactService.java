package edu.restfull.service.controller;




import edu.restfull.model.Contact;

import java.util.List;

/**
 * Created by Admin on 13.09.2016.
 */
public interface ContactService {
    List findAll();
    Contact findById(Long id);
    void save(Contact contact);
    void delete(Contact contact);
    Long countAll();
}
