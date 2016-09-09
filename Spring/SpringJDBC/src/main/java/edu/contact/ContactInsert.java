package edu.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Admin on 09.09.2016.
 */
public class ContactInsert  extends SqlUpdate {
    private static final String SQL_INSERT_CONTACT="INSERT INTO contact.contact(first_name,last_name,birth_date)" +
                                                    "VALUES (:first_name,:last_name,:birth_date)";

    public  ContactInsert(DataSource dataSource){
        super(dataSource,SQL_INSERT_CONTACT);
        super.declareParameter(new SqlParameter("first_name", Types.CHAR));
        super.declareParameter(new SqlParameter("last_name", Types.CHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.setGeneratedKeysColumnNames(new String[]{"id"});
        super.setReturnGeneratedKeys(true);
    }

}
