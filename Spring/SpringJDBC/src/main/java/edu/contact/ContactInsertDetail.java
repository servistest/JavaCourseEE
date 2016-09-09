package edu.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Admin on 09.09.2016.
 */
public class ContactInsertDetail extends BatchSqlUpdate {
    private static final String SQL_INSERT_CONTACT_DETAIL="INSERT INTO contact.contact_tel_detail (contact_id,tel_type,tel_number)" +
            "VALUES (:contact_id, :tel_type,:tel_number)";

    private static final int Batch_SIZE=10;
    public ContactInsertDetail(DataSource dataSource){
        super(dataSource,SQL_INSERT_CONTACT_DETAIL);
        super.declareParameter(new SqlParameter("contact_id", Types.INTEGER));
        super.declareParameter(new SqlParameter("tel_type", Types.VARCHAR));
        super.declareParameter(new SqlParameter("tel_number", Types.VARCHAR));
        super.setBatchSize(Batch_SIZE);

    }
}
