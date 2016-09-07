package edu.contact;

import java.util.List;

/**
 * Created by Admin on 06.09.2016.
 */
public interface ConatactDao {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Contact contact);
    void update(Contact contact);
    void delete(Contact contact);
}
