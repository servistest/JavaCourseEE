package edu.springjpa.dao;

import edu.springjpa.model.Contact;
import org.jboss.resteasy.annotations.interception.RedirectPrecedence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Admin on 15.09.2016.
 */
@Service( value = "jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
private static final Logger log= LoggerFactory.getLogger(ContactServiceImpl.class);
    @PersistenceContext
    private EntityManager emf;

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAll() {
        return emf.createNamedQuery("Contact.findAll",Contact.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAllWithDetail() {
        return emf.createNamedQuery("Contact.findAllWithDetail",Contact.class).getResultList();
    }

    @Override
    public Contact findById(Long id) {

        return emf.createNamedQuery("Contact.findById",Contact.class).setParameter("id",id).getSingleResult();
    }

    @Override
    public void save(Contact contact) {
        if (contact.getId()==null){
            emf.persist(contact);
            log.info("Insert new contact= {}  with id = {}",contact,contact.getId());
        }else {
            emf.merge(contact);
            log.info("Update new contact= {}  with id = {}",contact,contact.getId());
        }
    }

    @Override
    public void delete(Contact contact) {

    }
}
