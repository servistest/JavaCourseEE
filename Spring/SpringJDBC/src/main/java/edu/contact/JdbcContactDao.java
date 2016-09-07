package edu.contact;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 06.09.2016.
 */
public class JdbcContactDao implements ConatactDao,InitializingBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate=new JdbcTemplate(dataSource);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource==null){
            throw  new BeanCreationException("Must set dataSource on ContactDao");
        }
        if (jdbcTemplate==null){
            throw new BeanCreationException("Null  jdbcTemplate on ContactDao");
        }
    }

    @Override
    public List<Contact> findAllWithDetail() {
        String sql="SELECT CONTACT.ID,FIRST_NAME,LAST_NAME,BIRTH_DATE,CONTACT_TEL_DETAIL.ID,CONTACT_TEL_DETAIL.TEL_TYPE,CONTACT_TEL_DETAIL.TEL_NUMBER\n" +
                "from contact.CONTACT LEFT JOIN contact.CONTACT_TEL_DETAIL ON CONTACT.ID = CONTACT_TEL_DETAIL.CONTACT_ID";
        return namedParameterJdbcTemplate.query(sql,new ContactWithDetailExtractor());
    }

    @Override
    public List<Contact> findAll() {
        String sql="SELECT id,first_name, last_name, birth_date FROM contact.contact";
        return namedParameterJdbcTemplate.query(sql, new ContactMapper());
//        or
//        String sql="SELECT * FROM contact.contact";
//        List<Contact> contacts = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Contact>(Contact.class));
//        return contacts;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        String sql="SELECT id,first_name,last_name,birth_date FROM contact.contact WHERE first_name=:firstName";
        Map<String,Object> namedParameters=new HashMap<String, Object>();
        namedParameters.put("firstName",firstName);
        return namedParameterJdbcTemplate.query(sql,namedParameters,new ContactMapper());
    }

    @Override
    public String findLastNameById(Long id)
    {
        String sql="select last_name from contact.contact where id=:contactId";
        Map<String,Object> namedParameters=new HashMap<String, Object>();
        namedParameters.put("contactId",id);
        return namedParameterJdbcTemplate.queryForObject(sql,namedParameters,String.class);
//        or
//        String sql="select last_name from contact.contact where id=?";
//        return jdbcTemplate.queryForObject(sql,new Object[]{id},String.class);
    }

    @Override
    public String findFirstNameById(Long id) {
        String sql="select first_name from contact.contact where id=:contactId";
        Map<String,Object> namedParameters=new HashMap<String, Object>();
        namedParameters.put("contactId",id);
        return namedParameterJdbcTemplate.queryForObject(sql,namedParameters,String.class);
    }

    @Override
    public void insert(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void delete(Contact contact) {

    }

    private static final class ContactMapper implements RowMapper<Contact>{
        @Override
        public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
            Contact contact=new Contact();
            contact.setId(resultSet.getLong("id"));
            contact.setFirstName(resultSet.getString("first_name"));
            contact.setLastName(resultSet.getString("last_name"));
            contact.setBirthDate(resultSet.getDate("birth_date"));
            return contact;
        }
    }

    private static final class ContactWithDetailExtractor implements ResultSetExtractor<List<Contact>>{

        @Override
        public List<Contact> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            ArrayList<Contact> listContacts=new ArrayList<>();
            while (resultSet.next()){
                Contact contact=new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setBirthDate(resultSet.getDate("birth_date"));
                listContacts.add(contact);
            }
            return listContacts;
        }
    }

}
