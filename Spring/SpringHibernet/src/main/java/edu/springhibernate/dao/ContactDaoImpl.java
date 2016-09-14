package edu.springhibernate.dao;

import edu.springhibernate.entity.Contact;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Admin on 13.09.2016.
 */
@Repository(value = "contactDao")
@Transactional
public class ContactDaoImpl implements ContactDao {
    private static final Logger log= LoggerFactory.getLogger(ContactDaoImpl.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return  sessionFactory.getCurrentSession().createQuery("FROM Contact c ").list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {

        return sessionFactory.getCurrentSession().getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Override
    public Contact findById(Long id) {
        return (Contact) sessionFactory.getCurrentSession().getNamedQuery("Contact.findById").
                setParameter("id",id).uniqueResult();
    }

    @Override
    public void save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
    }

    @Override
    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().remove(contact);
    }
}
