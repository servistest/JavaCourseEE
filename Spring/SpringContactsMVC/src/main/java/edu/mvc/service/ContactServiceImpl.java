package edu.mvc.service;

import edu.mvc.dao.ContactDao;
import edu.mvc.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Map;

/**
 * Created by ALex on 13.11.2016.
 */
public class ContactServiceImpl implements ContactService {
    private static final Logger log= LoggerFactory.getLogger(ContactServiceImpl.class);
    private ContactDao contactDao;

    public  ContactServiceImpl( ContactDao contactDao){
        this.contactDao=contactDao;
    }

    @Override
    public Contact findContactById(Long id) {
        Assert.notNull(id,"id must not be null");
        Assert.isTrue(id>0,"id must greater than 0 ");
        return contactDao.findContactById(id);
    }

    @Override
    public List<Map<String, Object>> findFirstNameAndLastNameById(Long id) {
        Assert.notNull(id,"id must not be null");
        Assert.isTrue(id>0,"id must greater than 0 ");
        return contactDao.findFirstNameAndLastNameById(id);
    }

    @Override
    public String findFirstNameById(Long id) {
        Assert.notNull(id,"id must not be null");
        Assert.isTrue(id>0,"id must greater than 0 ");
        return contactDao.findFirstNameById(id);
    }

    @Override
    public List<Contact> findAllContacts() {
        return contactDao.findAllContacts();
    }

    @Override
    public Long save(Contact contact) {
        Assert.notNull(contact.getFirstName(),"firstName must be not null");
        Assert.notNull(contact.getLastName(),"lastName must be not null");
        Long id=contactDao.save(contact);
        return id;
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id,"id must not be null");
        Assert.isTrue(id>0,"id must greater than 0 ");
        contactDao.delete(id);
    }

    @Override
    public void update(Contact contact) {
        Assert.notNull(contact.getId(),"id must not be null");
        Assert.isTrue(contact.getId()>0,"id must greater than 0 ");
        Assert.notNull(contact.getFirstName(),"First Name must not be null");
        Assert.notNull(contact.getLastName(),"Last Name must not be null");
        contactDao.update(contact);
    }

}
