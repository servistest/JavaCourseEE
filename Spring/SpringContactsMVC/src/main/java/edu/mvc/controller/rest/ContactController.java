package edu.mvc.controller.rest;

import edu.mvc.model.Contact;
import edu.mvc.service.ContactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Admin on 17.11.2016.
 */
//К ContactController применена аннотация @Controller, которая указывает, что это контроллер Spring MVC.
@Controller
//Аннотация @RequestMapping на уровне класса задает корневой URL, который будет обрабатываться контроллером
//В этом случае все URL с префиксом /SpringContactsMVC/contacts будут направляться данному  контроллеру
@RequestMapping(value = "/contacts")
public class ContactController {
    Logger log= LoggerFactory.getLogger(ContactController.class);

    ContactService contactService;

//    Внутри метода list () извлекается список контактов, который сохраняется в интерфейсе
//    Model, экземпляр реализации которого передается методу средой Spring МVС.
    @RequestMapping(method = RequestMethod.GET)
    public String  list(Model uiModel){
        log.info("Listing contacts");
        List<Contact> contacts=contactService.findAllContacts();
        uiModel.addAttribute("contacts",contacts);
        log.info("No. of contacts = {}",contacts.size());
//        возвращается логическое имя представления contacts/list
        return "contacts/list";
//        В конфигурации сервлета диспетчера в качестве распознавателя представлений указан
//        InternalResourceViewResolver, а файл имеет префикс /WEB-INF/views/ и суффикс .jspx.
//        В результате Spring МVС выберет для представления файл /WEB-INF/views/contacts/list.jspx.
    }
    @RequestMapping(value = "/contacts/{id}")
//    Аннотация @PathVariable, которая заставляет Spring MVC извлекать id из URL и помещать  его в этот аргумент.
    public String show(@PathVariable("id") Long id, Model uiModel){
        log.info("Show contact");
        Contact contact=contactService.findContactById(id);
        uiModel.addAttribute("contact",contact);
        return "/contacts/show";
    }

    @Autowired
    public void setContactService(ContactService contactService){
        this.contactService=contactService;
    }
}
