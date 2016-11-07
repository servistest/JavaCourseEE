package edu.springtest.controller;

import edu.springtest.model.Contact;
import edu.springtest.model.Contacts;
import edu.springtest.service.controller.ContactController;
import edu.springtest.service.controller.ContactService;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by ALex on 06.11.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContactControllerWithAnnotationTest {
    @Spy
    private final List<Contact> contacts = new ArrayList<Contact>();
    @Mock
    ContactService contactService;
    @InjectMocks
    ContactController contactController;

    @Before
    public void initContacts() {
        Contact contact = new Contact();
        contact.setFirstName("Alex");
        contact.setLastName("Smirnov");
        contact.setBirthDate(new DateTime());
        contacts.add(contact);
    }

    @Test
    public void shouldListDataWhenAddContact() {
        when(contactService.findAll()).thenReturn(contacts);
        Contacts listContacts = contactController.listData();
        assertEquals(1, listContacts.getContacts().size());
    }

    @Test
    public void shouldReturnCreatedContact(){
        Contact contact=new Contact();
        contact.setId(1999L);
        contact.setFirstName("Igor");
        contact.setLastName("Titov");
        when(contactService.save(contact)).then(new Answer<Contact>() {

            @Override
            public Contact answer(InvocationOnMock invocationOnMock) throws Throwable {
                contacts.add(contact);
                return contact;
            }
        });
        Contact returnContact=contactController.create(contact);
        assertEquals(Long.valueOf(1999),returnContact.getId());
        assertEquals("Igor",returnContact.getFirstName());
        assertEquals("Titov",returnContact.getLastName());
        assertEquals(2,contacts.size());
    }
    @Test
    public void shouldReturnErrorCreatedContact(){
        Contact contact=new Contact();
        contact.setId(1999L);
        contact.setFirstName("Igor");
        contact.setLastName("Titov");
        when(contactService.save(contact)).then(new Answer<Contact>() {

            @Override
            public Contact answer(InvocationOnMock invocationOnMock) throws Throwable {
                contacts.add(contact);
                return contact;
            }
        });
        Contact returnContact=contactController.create(contact);
        assertEquals(Long.valueOf(1999),returnContact.getId());
        assertEquals("Igor",returnContact.getFirstName());
        assertEquals("Titov",returnContact.getLastName());
        assertEquals(2,contacts.size());
    }

    @After
    public void cleatInit(){
        contacts.clear();
    }

}
