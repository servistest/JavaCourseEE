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
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Admin on 26.10.2016.
 */

public class ContactControllerSpringTest {
    private final List<Contact> contacts = new ArrayList<Contact>();

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void initContacts() {
        Contact contact = new Contact();
        contact.setFirstName("Alex");
        contact.setLastName("Smirnov");
        contact.setBirthDate(new DateTime());
        contacts.add(contact);
    }

    @Test
    public void testListData() {
        ContactService contactService = mock(ContactService.class);
        when(contactService.findAll()).thenReturn(contacts);
        ContactController contactController = new ContactController();
        ReflectionTestUtils.setField(contactController, "contactService", contactService);
        ExtendedModelMap extendedModelMap = new ExtendedModelMap();
        extendedModelMap.addAttribute("contacts", contactController.listData());
        Contacts listContacts = (Contacts) extendedModelMap.get("contacts");
        assertEquals(1, listContacts.getContacts().size());
    }

    //or so...
    @Test
    public void shouldListDataWhenAddContact() {
        ContactService contactService = mock(ContactService.class);
        when(contactService.findAll()).thenReturn(contacts);
        ContactController contactController = new ContactController();
        ReflectionTestUtils.setField(contactController, "contactService", contactService);
        Contacts listContacts = contactController.listData();
        assertEquals(1, listContacts.getContacts().size());

    }

    @Test
    public void shouldReturnCreatedContact(){
        Contact contact=new Contact();
        contact.setId(1999L);
        contact.setFirstName("Igor");
        contact.setLastName("Titov");
        ContactService contactService =mock(ContactService.class);
        when(contactService.save(contact)).then(new Answer<Contact>() {

            @Override
            public Contact answer(InvocationOnMock invocationOnMock) throws Throwable {
                contacts.add(contact);
                return contact;
            }
        });
        ContactController contactController=new ContactController();
        ReflectionTestUtils.setField(contactController,"contactService",contactService);
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


