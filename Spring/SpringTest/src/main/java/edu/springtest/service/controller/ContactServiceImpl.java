package edu.springtest.service.controller;

import com.google.common.collect.Lists;

import edu.springtest.dao.ContactRepository;
import edu.springtest.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Admin on 15.09.2016.
 */
@Service( value = "contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final Logger log= LoggerFactory.getLogger(ContactServiceImpl.class);
//
//    @Override
//    public List findAll() {
//        ArrayList<Contact> contacts=new ArrayList<>();
//        Contact contact=new Contact();
//        contact.setFirstName("Alex");
//        contact.setLastName("Rogov");
//        contact.setBirthDate(new DateTime());
//        contacts.add(contact);
//        return contacts;
//    }
//
//    @Override
//    public Contact findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public void save(Contact contact) {
//
//    }
//
//    @Override
//    public void delete(Contact contact) {
//
//    }
//
//    @Override
//    public Long countAll() {
//        return null;
//    }

//
    @Autowired
    private ContactRepository contactRepository;

     @PersistenceContext
     private EntityManager emf;

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAll() {
        return  Lists.newArrayList(contactRepository.findAll()) ;
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
     return contactRepository.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    public Long countAll() {
        return contactRepository.count();
    }

    public ContactRepository getContactRepository() {
        return contactRepository;
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
