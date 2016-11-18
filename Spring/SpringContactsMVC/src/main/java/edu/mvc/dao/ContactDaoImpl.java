package edu.mvc.dao;

import com.google.common.collect.Lists;
import edu.mvc.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 14.11.2016.
 */
public class ContactDaoImpl implements ContactDao,InitializingBean {
    private static final String SQL_FIND_CONTACT_BY_ID="SELECT * FROM mvc.Contact WHERE id =:id";
    private static final String SQL_FIND_FIRST_NAME_BY_ID="SELECT first_name FROM mvc.Contact WHERE id =:id";
    private static final String SQL_FIRST_NAME_AND_LAST_NAME_BY_ID="SELECT first_name,last_name FROM mvc.Contact WHERE id =:id";
    private static final String SQL_FIND_ALL_CONTACTS="SELECT * FROM mvc.Contact";
    private static final String SQL_SAVE_CONTACT="INSERT into mvc.Contact(first_name,last_name,birth_date,description,photo) " +
                                "VALUES (:firstName,:lastName,:birthDate,:description,:photo)";
    private static final String SQL_UPDATE_CONTACT="UPDATE mvc.Contact SET first_name=:firstName,last_name=:lastName," +
                                "description=:description,birth_date=:birthDate, photo=:photo WHERE id=:id ";
    private static final String SQL_DELETE_CONTACT="DELETE FROM  mvc.Contact WHERE id =:id";

    private static final Logger log= LoggerFactory.getLogger(ContactDaoImpl.class);


    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


   public ContactDaoImpl(DataSource dataSource){
       this.dataSource=dataSource;
       jdbcTemplate=new JdbcTemplate(dataSource);
       namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
   }
    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource==null){
            throw  new BeanCreationException("Must set dataSource !!!");
        }
        if (jdbcTemplate==null){
            throw new BeanCreationException("jdbcTemplate is null!!!");
        }
        if (namedParameterJdbcTemplate==null){
            throw new BeanCreationException("namedParameterJdbcTemplate is null!!!");
        }
    }

    @Override
    public Contact findContactById(Long id) {
        log.info("Find contact by id={} ",id);
        Map<String,Object> namedParameters=new HashMap<>();
        namedParameters.put("id",id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_CONTACT_BY_ID,namedParameters,new ContactRowMapper());
    }

    @Override
    public List<Map<String,Object>> findFirstNameAndLastNameById(Long id) {
        log.info("Find First Name and Last Name by id={} ",id);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("id",id);
        return namedParameterJdbcTemplate.queryForList(SQL_FIRST_NAME_AND_LAST_NAME_BY_ID,parameters);

    }

    @Override
    public String findFirstNameById(Long id) {
        log.info("Find First Name by id={} ",id);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("id",id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_FIRST_NAME_BY_ID,parameters,String.class);
    }

    @Override
    public List<Contact> findAllContacts() {
        log.info("Find all contacts ");
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_CONTACTS,new ContactRowMapper());
    }

    @Override
    public Long save(Contact contact) {
//        GeneratedKeyHolder()  generate одни и теже ключи при кажом запусе программы!!!!?????
//        например я создаю 2 контакта - они добавляются с id 16 и 17.
//        потом программу запускаю еще раз - опять переписываются Новые контакты с  id 16 и 17!!!
//        !!!ПОТОМУ ЧТО при каждом запуске инициализируется база ;)  !!!
//        parameter "new String[]{"id"}" - is  id-fields. Перечисляется список полей  с ключами в таблице.
        log.info("Save new contact ={} ",contact);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_SAVE_CONTACT, mapSqlParameterSource(contact),keyHolder,new String[]{"id"});
        Long id=keyHolder.getKey().longValue();
        contact.setId(id);
        return id;
   }

    @Override
    public void delete(Long id) {
        log.info("Delete  contact with id ={} ",id);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("id",id);
        namedParameterJdbcTemplate.update(SQL_DELETE_CONTACT,parameters);
    }

    @Override
    public void update(Contact contact) {
        log.info("Update  contact ={} ",contact);
        namedParameterJdbcTemplate.update(SQL_UPDATE_CONTACT,mapSqlParameterSource(contact));

    }

    private MapSqlParameterSource mapSqlParameterSource(Contact contact) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", contact.getId());
        parameterSource.addValue("firstName",contact.getFirstName());
        parameterSource.addValue("lastName",contact.getLastName());
        parameterSource.addValue("description",contact.getDescription());
        parameterSource.addValue("birthDate",contact.getBirthDate());
        parameterSource.addValue("photo",contact.getPhoto());
        return parameterSource;
    }
    private static final class ContactRowMapper implements RowMapper<Contact>{
        @Override
        public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
           Contact contact=new Contact();
            contact.setId(resultSet.getLong("id"));
            contact.setFirstName(resultSet.getString("first_name"));
            contact.setLastName(resultSet.getString("last_Name"));
            contact.setDescription(resultSet.getString("description"));
            contact.setBirthDate(resultSet.getTimestamp("birth_date"));
            contact.setPhoto(resultSet.getBytes("photo"));
            return contact;
        }
    }


}
