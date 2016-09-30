package edu.springjta.service;

import com.google.common.collect.Lists;
import edu.springjta.dao.ContactRepository;
import edu.springjta.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

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
    @Transactional(readOnly = true,propagation = Propagation.NEVER)
    public Long countAll() {
        return contactRepository.countAllContacts();
    }

    @Override
    public void save(Contact contact) {
      contactRepository.save(contact);
    }

    public ContactRepository getContactRepository() {
        return contactRepository;
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


}
