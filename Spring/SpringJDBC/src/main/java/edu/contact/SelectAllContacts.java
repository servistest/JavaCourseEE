package edu.contact;

import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 08.09.2016.
 */
public class SelectAllContacts extends MappingSqlQuery<Contact> {
   private static final String SQL_SELECT_ALL_CONTACT="select id,first_name,last_name,birth_date from contact.contact";

    public SelectAllContacts(DataSource dataSource){
        super(dataSource,SQL_SELECT_ALL_CONTACT);
    }
    @Override
    protected Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact=new Contact();
        contact.setId(resultSet.getLong("id"));
        contact.setFirstName(resultSet.getString("first_name"));
        contact.setLastName(resultSet.getString("last_name"));
        contact.setBirthDate(resultSet.getDate("birth_date"));
        return contact;
    }
}
