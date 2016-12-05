package edu.mvc.controller.rest;

import edu.mvc.controller.message.Message;
import edu.mvc.controller.message.UrlUtil;
import edu.mvc.model.Contact;
import edu.mvc.service.ContactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 17.11.2016.
 */
//К ContactController применена аннотация @Controller, которая указывает, что это контроллер Spring MVC.

//Аннотация @RequestMapping на уровне класса задает корневой URL, который будет обрабатываться контроллером
//В этом случае все URL с префиксом /SpringContactsMVC/contacts будут направляться данному  контроллеру
@Controller
@RequestMapping(value = "/contacts")
public class ContactController {
    Logger log= LoggerFactory.getLogger(ContactController.class);

    private ContactService contactService;
    private MessageSource messageSource;


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
    @RequestMapping(value = "/{id}",method = RequestMethod.GET )
//    Аннотация @PathVariable, которая заставляет Spring MVC извлекать id из URL и помещать  его в этот аргумент.
    public String show(@PathVariable("id") Long id, Model uiModel){
        log.info("Show contact");
        Contact contact=contactService.findContactById(id);
        uiModel.addAttribute("contact",contact);
        return "contacts/show";
    }

//    Spring MVC попытается связать отправленные  данные с объектом предметной области Contact и автоматически выполнит
//    преобразование типов и форматирование.
    @RequestMapping(value = "/{id}",params = "form",method = RequestMethod.POST)
    public String update(Contact contact, Model uiModel, BindingResult bindingResult,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,Locale locale){
        log.info("Updating contact");
//        В случае обнаружения ошибок привязки (например, дата рождения была введена в неверном формате)
//        эти ошибки будут сохранены в интерфейсе BindingResult (из пакета org. springframework.validation),
//        а сообщения об ошибках - в модели, приводя к повторному отображению представления редактирования
        if(bindingResult.hasErrors()){
            uiModel.addAttribute("message",new Message("error",
                    messageSource.getMessage("contact_save_fail", new Object[]{} ,locale)));
            uiModel.addAttribute("contact",contact);
            return "contacts/update";
        }
//        clear model
        uiModel.asMap().clear();
//        Если привязка прошла успешно, данные сохраняются, и возвращается логическое
//        имя представления просмотра контакта с указанием redirect: в качестве префикса.
//        Нам нужно отобразить сообщение после перенаправления,поэтому мы должны использовать
//        метод RedirectAttributes.addFlashAttribute () для отображения
//        сообщения об успехе в представлении просмотра контакта.  Flаsh-атрибуты в Spring MVC
//        временно сохраняются  перед перенаправлением (обычно в сеансе) и будут доступны запросу после
//        перенаправления, а затем они немеменно удаляются
        redirectAttributes.addFlashAttribute("message",
                new Message("success",messageSource.getMessage("contact_save_success",new Object[]{},locale)));
        contactService.update(contact);
        return "redirect:/contacts/"+ UrlUtil.encodeUrlPathSegment(contact.getId().toString(),httpServletRequest);
    }

    @RequestMapping(value = "/{id}",params = "form",method = RequestMethod.GET)
    public String updateForm(@PathVariable("id")Long id, Model uiModel){
        log.info("Search contact by id for update form");
        uiModel.addAttribute("contact",contactService.findContactById(id));
        return "contacts/update";
    }

    @RequestMapping(params = "form",method = RequestMethod.POST)
    public String create(Contact contact, Model uiModel,BindingResult bindingResult,
                         Locale locale,HttpServletRequest httpServletRequest,RedirectAttributes redirectAttributes){
        log.info("Create new contact");
        if(bindingResult.hasErrors()){
            uiModel.addAttribute("message",new Message("error",messageSource.getMessage("contact_save_fail",new Object[]{},locale)));
            uiModel.addAttribute("contact",contact);
            return "contacts/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message",new Message("success",messageSource.getMessage("contact_save_success",new Object[] {},locale)));
        uiModel.addAttribute("contact",contactService.save(contact));
        return "redirect:/contacts/"+UrlUtil.encodeUrlPathSegment(contact.getId().toString(),httpServletRequest);
    }

    @RequestMapping(params = "form",method = RequestMethod.GET)
    public String createForm(Model uiModel){
        Contact contact=new Contact();
        uiModel.addAttribute("contact",contact);
        return "contacts/create";
    }

    @Autowired
    public void setContactService(ContactService contactService){
        this.contactService=contactService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource=messageSource;
    }
}
