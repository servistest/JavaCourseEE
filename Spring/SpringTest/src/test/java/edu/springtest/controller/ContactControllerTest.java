package edu.springtest.controller;

import edu.springtest.model.Contact;
import edu.springtest.model.Contacts;
import edu.springtest.service.controller.ContactController;
import edu.springtest.service.controller.ContactService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by Admin on 26.10.2016.
 */
public class ContactControllerTest {
    private  final List<Contact> contacts=new ArrayList<Contact>();
//    @InjectMocks
//    ContactService contactService;
//    @Mock
//    Iterator iterator;
    @Spy
    List<String> spyList=new ArrayList<>();

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void initContacts(){
        Contact contact=new Contact();
        contact.setFirstName("Alex");
        contact.setLastName("Smirnov");
        contact.setBirthDate(new DateTime());
        contacts.add(contact);
    }




    @Test
    public void testListData(){
        ContactService contactService= mock(ContactService.class);
        when(contactService.findAll()).thenReturn(contacts);
        ContactController contactController=new ContactController();
        ReflectionTestUtils.setField(contactController,"contactService",contactService);
        ExtendedModelMap extendedModelMap=new ExtendedModelMap();
        extendedModelMap.addAttribute("contacts",contactController.listData());
        Contacts listContacts= (Contacts) extendedModelMap.get("contacts");
        assertEquals(1,listContacts.getContacts().size());
    }


    @Test
    public void testVerify(){
        ContactService contactService=mock(ContactService.class);
        when(contactService.findAll()).thenReturn(contacts);
        contactService.findAll();
        contactService.findAll();
        verify(contactService,times(2)).findAll();
//        никогда не вызывался
//        verify(contactService,never()).findAll();
//        вызывался не более 3 раз
        verify(contactService,atMost(3)).findAll();
    }

}
