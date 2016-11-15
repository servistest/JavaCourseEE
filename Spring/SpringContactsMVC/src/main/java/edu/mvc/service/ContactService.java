package edu.mvc.service;

import edu.mvc.model.Contact;

import java.util.List;
import java.util.Map;

/**
 * Created by ALex on 13.11.2016.
 */
public interface ContactService {
    public Contact findContactById(Long id);
    public List<Map<String,Object>>  findFirstNameAndLastNameById(Long id);
    public String findFirstNameById(Long id);
    public List<Contact> findAllContacts();
    public void save (Contact contact);
    public void delete(Contact contact);
    public void update(Contact contact);
}