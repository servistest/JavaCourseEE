package edu.mvc.dao;

import edu.mvc.model.Contact;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 14.11.2016.
 */
public interface ContactDao {
    public Contact findContactById(Long id);
    public List<Map<String,Object>>  findFirstNameAndLastNameById(Long id);
    public String findFirstNameById(Long id);
    public List<Contact> findAllContacts();
    public void save (Contact contact);
    public void delete(Contact contact);
    public void update(Contact contact);

}
