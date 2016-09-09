package edu.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Admin on 08.09.2016.
 */
public class SelectContactByFirstName extends MappingSqlQuery<Contact> {
    private static final String SQL_SELECT_CONTACT_BY_FIRST_NAME="SELECT id,first_name,last_name,birth_date " +
            "FROM contact.contact WHERE first_name = :first_name";

    public SelectContactByFirstName(DataSource dataSource){
        super(dataSource,SQL_SELECT_CONTACT_BY_FIRST_NAME);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
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
