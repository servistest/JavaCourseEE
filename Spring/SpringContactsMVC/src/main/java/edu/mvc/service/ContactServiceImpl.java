package edu.mvc.service;

import edu.mvc.dao.ContactDao;
import edu.mvc.model.Contact;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Map;

/**
 * Created by ALex on 13.11.2016.
 */
public class ContactServiceImpl implements ContactService {

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
        return null;
    }

    @Override
    public void save(Contact contact) {

    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }


}
