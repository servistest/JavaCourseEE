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
    public Long save (Contact contact);
    public void delete(Long id);
    public void update(Contact contact);

}
