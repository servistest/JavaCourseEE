package edu.mvc.controller.rest;

import com.google.common.collect.Lists;
import edu.mvc.controller.message.Message;
import edu.mvc.controller.message.UrlUtil;
import edu.mvc.model.Contact;
import edu.mvc.model.ContactGrid;
import edu.mvc.service.ContactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
//            возвращаем имя представления , в данном случае /WEB-INF/views/contacts/list.jspx
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
//            возвращаем имя представления , в данном случае /WEB-INF/views/contacts/show.jspx
        return "contacts/show";
    }

//    Spring MVC попытается связать отправленные  данные с объектом предметной области Contact и автоматически выполнит
//    преобразование типов и форматирование.
//    Чтобы включить проверку достоверности JSR-349 во время процесса привязки данных, нужно просто применить
//    аннотацию @Valid к аргументу метода
//    "BindingResult bindingResult" в параметрах !!!ДОЛЖЕН!! идти сразу после объекта (@Valid Contact contact) с
//    которым мы связываем результаты привязки, иначе на экран не выводятся ссобщения об ошибках, а выдается в логе exception:
//    validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors
    @RequestMapping(value = "/{id}",params = "form",method = RequestMethod.POST)
    public String update(@Valid Contact contact, BindingResult bindingResult,Model uiModel,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
        log.info("Updating contact");
//        В случае обнаружения ошибок привязки (например, дата рождения была введена в неверном формате)
//        эти ошибки будут сохранены в интерфейсе BindingResult (из пакета org. springframework.validation),
//        а сообщения об ошибках - в модели, приводя к повторному отображению представления редактирования
        if(bindingResult.hasErrors()){
            uiModel.addAttribute("message",new Message("error",
                    messageSource.getMessage("contact_save_fail", new Object[]{} ,locale)));
            uiModel.addAttribute("contact",contact);
//            возвращаем имя представления , в данном случае /WEB-INF/views/contacts/update.jspx
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

    @RequestMapping(value = "/{id}",params = "formDelete",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, Model uiModel){
        log.info("Delete contact ... ");
        contactService.delete(id);

//        uiModel.
        return "redirect:/contacts/";
    }
    @RequestMapping(value = "/{id}",params = "formDelete",method = RequestMethod.GET)
    public String deleteForm(@PathVariable("id")  Long id, Model uiModel){
        uiModel.addAttribute("contact",contactService.findContactById(id));
//            возвращаем имя представления , в данном случае /WEB-INF/views/contacts/delete.jspx
        return "contacts/delete";
    }

//    Чтобы включить проверку достоверности JSR-349 во время процесса привязки данных, нужно  применить
//    аннотацию @Valid к аргументу метода.
//    BindingResult bindingResult в параметрах !!!ДОЛЖЕН!! идти сразу после объекта (@Valid Contact contact) с
//    которым мы связываем результаты привязки, иначе на экран не выводятся ссобщения об ошибках, а выдается в логе exception:
//    validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors
    @RequestMapping(params = "form",method = RequestMethod.POST)
    public String create(@Valid Contact contact,BindingResult bindingResult,Model uiModel,
                         HttpServletRequest httpServletRequest,
                         RedirectAttributes redirectAttributes,Locale locale){
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

//    Метод listGrid () метод обрабатывает запрос Ajax, читает параметры (номер страницы, количество записей на страницу,
//    поле, по которому производится    сортировка, порядок сортировки) из запроса (имена параметров в примере кода соответствуют
//    принятым по умолчанию в jqGrid), конструирует экземпляр класса  PageRequest, реализующий интерфейс Раgеаblе, и затем вызывает
//    метод  ContactService. findAllByPage () для получения  страничных данных. После этого создается экземпляр класса ContactGrid,
//    который возвращает компонент   jqGrid в формате JSON.
    @RequestMapping(value = "/listgrid",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public ContactGrid listGrid(@RequestParam(value = "page",required = false) Integer page,
                                @RequestParam(value = "rows",required = false) Integer rows,
                                @RequestParam(value = "sidx",required = false) String sortBy,
                                @RequestParam(value = "sord",required = false) String order){
        log.info("Listing contacts for grid with page:{},rows: {}",page,rows);
        log.info("Listing contacts for grid with sort:{},order:{} ");
        //Обработать поле по которому идет сортировка
        Sort sort=null;
        String orderBy=sortBy;
        if(orderBy!=null && orderBy.equals("birthDateString")) orderBy="birthDate";
        if (orderBy!=null && order!=null){
            if(order.equals("desc")){
                sort=new Sort(Sort.Direction.DESC,orderBy);
            }else {
                sort=new Sort(Sort.Direction.ASC,orderBy);
            }
        }
        // Сконструировать страничный запрос для текущей страницы. Нумерация страниц для Spring Data JPA начинается с О,
        // тогда как в jqGrid - с 1
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }
        Page<Contact> contactPage = contactService.findAllByPage(pageRequest);

        // Сконструировать сетку, которая вернет данные в формате JSON
        ContactGrid contactGrid = new ContactGrid();

        contactGrid.setCurrentPage(contactPage.getNumber() + 1);
        contactGrid.setTotalPages(contactPage.getTotalPages());
        contactGrid.setTotalRecords(contactPage.getTotalElements());
        contactGrid.setContactData(Lists.newArrayList(contactPage.iterator()));
        return contactGrid;

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
