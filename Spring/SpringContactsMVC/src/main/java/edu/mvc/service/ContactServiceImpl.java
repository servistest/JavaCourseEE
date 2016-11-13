package edu.mvc.service;

import com.google.common.collect.Lists;
import edu.mvc.dao.ContactRepository;
import edu.mvc.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ALex on 13.11.2016.
 */
public class ContactServiceImpl implements ContactService {


    @Autowired
    ContactRepository contactRepository;
    @Override
    public void findById(Long id) {
        contactRepository.findOne(id);
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }
}
