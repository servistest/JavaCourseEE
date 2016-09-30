package edu.validation.service;




import edu.validation.model.Contact;

import java.util.List;

/**
 * Created by Admin on 13.09.2016.
 */
public interface ContactService {
    List findAll();
    Contact findById(Long id);
    void save(Contact contact);
    Long countAll();
}
