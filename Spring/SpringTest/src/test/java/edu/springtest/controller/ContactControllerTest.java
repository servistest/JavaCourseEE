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
    public void spyListTest(){
        List<String> spyList=Mockito.spy(new ArrayList<String>());
        spyList.add("One");
        spyList.add("One");
        spyList.add("Two");
        assertEquals(3,spyList.size());

        when(spyList.size()).thenReturn(20);
        assertEquals(20,spyList.size());

        verify(spyList,times(2)).add("One");
        verify(spyList,times(1)).add("Two");
        assertEquals(20,spyList.size());
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
    public void iteratorTest(){
        Iterator iterator=mock(Iterator.class);
        when(iterator.next()).thenReturn("Yoo").thenReturn("Hoo!");
        String result =iterator.next()+" " +iterator.next();
        assertEquals(result,"Yoo Hoo!");
    }

     @Test
    public void anyValueArgs(){
         Comparable comparable=mock(Comparable.class);
         when(comparable.compareTo(anyInt())).thenReturn(-29);
         assertEquals(comparable.compareTo(10),-29);
     }

    @Test(expected = IOException.class)
    public void testThrowException() throws IOException{
        OutputStream outputStream=mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
        doThrow(new IOException()).when(outputStream).close();
        outputStreamWriter.close();
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
