package edu.mvc.dao;

import com.google.common.collect.Lists;
import edu.mvc.model.Contact;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
    private static final String SQL_FIRST_NAME_AND_LAST_NAME_BY_ID="SELECT first_name,last_name FROM mvc.Contact WHERE id =:id";
    private static final String SQL_FIND_ALL_CONTACTS="SELECT * FROM mvc.Contact ";
    private static final String SQL_SAVE_CONTACT="INSERT into mvc.Contact(first_name,last_name,birth_date,description,photo) " +
            "VALUES (:firstName,:lastName,:birthDate,:description,:photo)";
    private static final String SQL_UPDATE_CONTACT="UPDATE mvc.Contact SET first_name=:first_name,last_name=:last_name," +
            "birth_date=:birth_date,description=:description,photo=:photo WHERE id=:id ";
    private static final String SQL_DELETE_CONTACT="DELETE FROM  mvc.Contact WHERE id =:id";


    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


   public ContactDaoImpl(DataSource dataSource){
       this.dataSource=dataSource;
       jdbcTemplate=new JdbcTemplate(dataSource);
       namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
   }

    @Override
    public Contact findContactById(Long id) {
        Map<String,Object> namedParameters=new HashMap<>();
        namedParameters.put("id",id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_CONTACT_BY_ID,namedParameters,new ContactRowMapper());
    }


//    public String findFirstNameAndLastNameById(Long id) {
//        Map<String,Object> parameters=new HashMap<>();
//        parameters.put("id",id);
//        return null;
////                namedParameterJdbcTemplate.queryForObject(SQL_FIRST_NAME_AND_LAST_NAME_BY_ID,parameters,new ContactRowMapper());
//
//    }
    @Override
    public List<Map<String,Object>> findFirstNameAndLastNameById(Long id) {
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("id",id);
        return namedParameterJdbcTemplate.queryForList(SQL_FIRST_NAME_AND_LAST_NAME_BY_ID,parameters);

    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public List<Contact> findAllContacts() {
        return null;
    }

    @Override
    public void save(Contact contact) {

    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
            if (dataSource==null){
                throw  new BeanCreationException("Must set dataSource !!!");
            }
            if (jdbcTemplate==null){
                throw new BeanCreationException("Null  jdbcTemplate !!!");
            }
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

    private static final class FirstAnsLastNameRowMapper implements RowMapper<Contact>{
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
