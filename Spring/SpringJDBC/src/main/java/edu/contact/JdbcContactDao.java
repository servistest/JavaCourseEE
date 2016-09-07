package edu.contact;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Admin on 06.09.2016.
 */
public class JdbcContactDao implements ConatactDao,InitializingBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate=new JdbcTemplate(dataSource);
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
    public List<Contact> findAll() {
//        jdbcTemplate.queryForObject("Select * from contact ", RowMapper<Contact>)
        return null;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id)
    {
        String sql="select firstName from contact where id=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},String.class);
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
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


}
