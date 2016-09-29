package edu.springjta.service;

import com.google.common.collect.Lists;
import edu.springjta.dao.ContactRepository;
import edu.springjta.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Admin on 15.09.2016.
 */
@Service( value = "contactServiceProgramTransaction")
@Repository
//@Transactional
public class ContactServiceProgramTransactionImpl implements ContactService {
    private static final Logger log= LoggerFactory.getLogger(ContactServiceProgramTransactionImpl.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    TransactionTemplate transactionTemplate;

    @PersistenceContext
    private EntityManager emf;


    @Override
    public List<Contact> findAll() {
        return  Lists.newArrayList(contactRepository.findAll()) ;
    }



    @Override
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override

    public Long countAll() {
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus transactionStatus) {
                log.info("transaction is new ? = {}",transactionStatus.isNewTransaction());
                log.info("transaction is RollBack ? = {}",transactionStatus.isRollbackOnly());
                return emf.createNamedQuery("Contact.countAll",Long.class).getSingleResult();

            }
        });

    }

    @Override
    public void save(Contact contact) {
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionTemplate.setIsolationLevelName("ISOLATION_READ_UNCOMMITTED");
        transactionTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {
                if (contact.getId()==null){
                    emf.persist(contact);
                }else {
                    emf.merge(contact);

                }
//                contactRepository.save(contact);
                return contact.getId();
            }
        });
    }

    public ContactRepository getContactRepository() {
        return contactRepository;
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
