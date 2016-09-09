package edu.contact;

import java.util.List;

/**
 * Created by Admin on 06.09.2016.
 */
public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    List<Contact> findAllWithDetail();
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Contact contact);
    void insertWithDetail(Contact contact);
    void insertWithDetail();
    void update(Contact contact);
    void delete(Contact contact);
}
