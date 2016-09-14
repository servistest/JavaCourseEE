package edu.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Admin on 12.09.2016.
 */
public class StoredFunctionFirstNameById extends SqlFunction<String> {
    private static final String SQL="SELECT contact.getFirstNameById(?)";
    public StoredFunctionFirstNameById (DataSource dataSource){
        super(dataSource,SQL);
        super.declareParameter(new SqlParameter(Types.INTEGER));
        super.compile();
    }
}
