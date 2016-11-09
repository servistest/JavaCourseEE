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

import static edu.springtest.model.Contact_.firstName;

/**
 * Created by Admin on 15.09.2016.
 */
@Service( value = "contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final Logger log= LoggerFactory.getLogger(ContactServiceImpl.class);
    private static final String SQL_NATIVE_FIND_ALL="SELECT * FROM contact";
    private static final String SQL_NATIVE_FIND_BY_FIRST_NAME_AND_LAST_NAME="SELECT c FROM Contact c WHERE c.firstName = :firstName and c.lastName =:lastName";

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
    public Contact findByFirstName(String firstName) {
        return emf.createNamedQuery("Contact.findByFirstName",Contact.class)
                                              .setParameter("firstName",firstName)
                                               .getResultList()
                                               .stream()
                                               .findFirst()
                                               .orElse(null);

//        or so ...
//        return  (Contact)emf.createQuery("SELECT c FROM Contact c WHERE c.firstName = :firstName")
//                                        .setParameter("firstName",firstName)
//                                        .getSingleResult();
    }

    @Override
    public Contact findByFirstNameAndLastName(String firstName, String lastName) {
          return   emf.createQuery(SQL_NATIVE_FIND_BY_FIRST_NAME_AND_LAST_NAME,Contact.class)
                  .setParameter("firstName",firstName)
                  .setParameter("lastName",lastName)
                  .getResultList()
                  .stream()
                  .findFirst()
                  .orElse(null);

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
