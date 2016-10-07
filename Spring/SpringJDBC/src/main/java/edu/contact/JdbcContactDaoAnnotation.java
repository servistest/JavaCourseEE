package edu.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import javax.sql.DataSource;
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
    private SelectFirstNameByID selectFirstNameById;
    private ContactUpdate contactUpdate;
    private ContactInsert contactInsert;
    private ContactInsertDetail contactInsertDetail;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllContacts=new SelectAllContacts(dataSource);
        this.selectContactByFirstName=new SelectContactByFirstName(dataSource);
        this.selectFirstNameById=new SelectFirstNameByID(dataSource);
        this.contactUpdate=new ContactUpdate(dataSource);
        this.contactInsert=new ContactInsert(dataSource);
        this.contactInsertDetail=new ContactInsertDetail(dataSource);
        this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
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
    public List<Contact> findAllWithDetail()
    {
        String sql="SELECT CONTACT.ID,FIRST_NAME,LAST_NAME,BIRTH_DATE,CONTACT_TEL_DETAIL.ID as contact_tel_id,CONTACT_TEL_DETAIL.TEL_TYPE,CONTACT_TEL_DETAIL.TEL_NUMBER\n" +
                "from contact.CONTACT LEFT JOIN contact.CONTACT_TEL_DETAIL ON CONTACT.ID = CONTACT_TEL_DETAIL.CONTACT_ID";
        return namedParameterJdbcTemplate.query(sql,new ContactWithDetailExtractor());

    }

    @Override
    public String findLastNameById(Long id) {

        String sql="select last_name from contact.contact where id=:contactId";
        Map<String,Object> namedParameters=new HashMap<String, Object>();
        namedParameters.put("contactId",id);
        return namedParameterJdbcTemplate.queryForObject(sql,namedParameters,String.class);
    }

    @Override
    public String findFirstNameById(Long id) {

        String sql="select first_name from contact.contact where id=:contactId";
        Map<String,Object> namedParameters=new HashMap<String, Object>();
        namedParameters.put("contactId",id);
        return namedParameterJdbcTemplate.queryForObject(sql,namedParameters,String.class);
    }

    @Override
    public String findFirstNameByIdFunction(Long id) {
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
