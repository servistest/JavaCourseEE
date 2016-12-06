package edu.mvc.controller.rest;

import edu.mvc.model.Contact;
import edu.mvc.model.Contacts;
import edu.mvc.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17.11.2016.
 */
@Controller
@RequestMapping(value = "/contactsOLD")
public class ContactControllerOLD {
    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public String HelloWorld(){
        return "Hello World!";
    }
    @RequestMapping(value = "/listData",method = RequestMethod.GET)
    @ResponseBody
    public Contacts listData(){

        List<Contact> contactList=contactService.findAllContacts();
        return new Contacts((ArrayList<Contact>) contactList);
    }
}
