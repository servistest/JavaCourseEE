package edu.restfull.service.controller;

import edu.restfull.model.Contact;
import edu.restfull.model.Contacts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Admin on 10.10.2016.
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    final Logger log= LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactService contactService;

    @ResponseBody
    @RequestMapping(value = "/listData",method = RequestMethod.GET)
    public Contacts listData(){
        return new Contacts(contactService.findAll());
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Contact findContactById(@PathVariable Long id){
        return contactService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Contact create (@RequestBody Contact contact){
        log.info("Create contact " , contact);
        contactService.save(contact);
        log.info("Contact create successfully with info{}", contact );
        return contact;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Contact update (@RequestBody Contact contact, @PathVariable Long id){
        log.info("Update contact {}" , contact);
        contactService.save(contact);
        log.info("Contact updated successfully with info {}", contact );
        return contact;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        Contact contact=contactService.findById(id);
        log.info("Delete contact {}",contact);
        contactService.delete(contact);
        log.info("Delete contact successfully {}",contact);
    }

}
