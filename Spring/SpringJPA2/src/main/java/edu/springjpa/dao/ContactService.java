package edu.springjpa.dao;

import edu.springjpa.model.*;

import java.util.List;

/**
 * Created by Admin on 13.09.2016.
 */
public interface ContactService {
    List findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    void save(Contact contact);
    void delete(Contact contact);
    List<Contact> findAllByNativeQuery();
}
