package edu.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 08.09.2016.
 */
@Repository("contactDao")
public class JdbcContactDaoAnnotation implements ContactDao {
    private static final Logger log= LoggerFactory.getLogger(JdbcContactDaoAnnotation.class);
    private DataSource dataSource;
    private SelectAllContacts selectAllContacts;
    private SelectContactByFirstName selectContactByFirstName;
    private SelectFirstNameById selectFirstNameById;
    private ContactUpdate contactUpdate;
    private ContactInsert contactInsert;
    private ContactInsertDetail contactInsertDetail;


    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllContacts=new SelectAllContacts(dataSource);
        this.selectContactByFirstName=new SelectContactByFirstName(dataSource);
        this.selectFirstNameById=new SelectFirstNameById(dataSource);
        this.contactUpdate=new ContactUpdate(dataSource);
        this.contactInsert=new ContactInsert(dataSource);
        this.contactInsertDetail=new ContactInsertDetail(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<Contact> findAll() {
        return selectAllContacts.execute();
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("first_name",firstName);
        return selectContactByFirstName.executeByNamedParam(paramMap);
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {

        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("id",id);
//        return selectFirstNameById.executeByNamedParam(paramMap);
        return null;
    }

    @Override
    public void insert(Contact contact) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("first_name",contact.getFirstName());
        paramMap.put("last_name",contact.getLastName());
        paramMap.put("birth_date",contact.getBirthDate());

        KeyHolder keyHolder=new GeneratedKeyHolder();
        contactInsert.updateByNamedParam(paramMap,keyHolder);

        contact.setId(keyHolder.getKey().longValue());
        log.info("Insert contact with id = {}",contact.getId());
    }

    @Override
    public void insertWithDetail(Contact contact) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("first_name",contact.getFirstName());
        paramMap.put("last_name",contact.getLastName());
        paramMap.put("birth_date",contact.getBirthDate());

        KeyHolder keyHolder=new GeneratedKeyHolder();
        contactInsert.updateByNamedParam(paramMap,keyHolder);

        contact.setId(keyHolder.getKey().longValue());
        log.info("Insert new Contact with id ={}",contact.getId());

        List<ContactTelDetail> listContactTelDetails=contact.getContactTelDetails();

        if (listContactTelDetails!=null){
            for(ContactTelDetail contactTelDetail:listContactTelDetails){
                paramMap=new HashMap<>();
                paramMap.put("contact_id",contact.getId());
                paramMap.put("tel_type",contactTelDetail.getTelType());
                paramMap.put("tel_number",contactTelDetail.getTelNumber());
                contactInsertDetail.updateByNamedParam(paramMap);
            }
        }
        contactInsertDetail.flush();
    }

    @Override
    public void insertWithDetail() {

    }

    @Override
    public void update(Contact contact) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("first_name",contact.getFirstName());
        paramMap.put("last_name",contact.getLastName());
        paramMap.put("birth_date",contact.getBirthDate());
        paramMap.put("id",contact.getId());
        contactUpdate.updateByNamedParam(paramMap);
    }

    @Override
    public void delete(Contact contact) {

    }
}
