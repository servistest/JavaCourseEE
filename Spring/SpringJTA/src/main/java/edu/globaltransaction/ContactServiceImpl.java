package edu.globaltransaction;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Admin on 15.09.2016.
 */
@Service( value = "globalJtaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final Logger log= LoggerFactory.getLogger(ContactServiceImpl.class);

//    @Autowired
    private ContactRepository contactRepository;

    @PersistenceContext(unitName = "emfA")
    private EntityManager emfA;

    @PersistenceContext(unitName = "emfB")
    private EntityManager emfB;

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
    public void save(Contact contact) {
        Contact contactB=new Contact();
        contactB.setFirstName(contact.getFirstName());
        contactB.setLastName(contact.getLastName());
        contactB.setBirthDate(contact.getBirthDate());
        if (contact.getId()==null){
            emfA.persist(contact);
            emfB.persist(contactB);
//            throw new JpaSystemException(new PersistenceException());
        }else {
            emfA.merge(contact);
            emfB.merge(contact);
        }
    }

    public ContactRepository getContactRepository() {
        return contactRepository;
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


}
