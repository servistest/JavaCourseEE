package edu.springhibernate.dao;

import edu.springhibernate.entity.Contact;

import java.util.List;

/**
 * Created by Admin on 13.09.2016.
 */
public interface ContactDao {
    List findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    void save(Contact contact);
    void delete(Contact contact);
}
