package edu.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Admin on 08.09.2016.
 */
public class ContactUpdate extends SqlUpdate {
    private static final String SQL_UPDATE_CONTACT="UPDATE contact.contact " +
            "SET  first_name=:first_name,last_name=:last_name,birth_date=:birth_date WHERE id=:id";

    public ContactUpdate(DataSource dataSource){
        super(dataSource,SQL_UPDATE_CONTACT);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
        super.declareParameter(new SqlParameter("last_name",Types.VARCHAR));
        super.declareParameter(new SqlParameter("first_name",Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date",Types.DATE));
    }
}
