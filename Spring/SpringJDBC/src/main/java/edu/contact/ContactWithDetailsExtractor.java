package edu.contact;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 09.09.2016.
 */
public final class ContactWithDetailExtractor implements ResultSetExtractor<List<Contact>> {

    @Override
    public List<Contact> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        ArrayList<Contact> listContacts=new ArrayList<>();
        Map<Long,Contact> map=new HashMap<>();

        Contact contact=null;
        while (resultSet.next()){
            Long id=resultSet.getLong("id");
            contact=map.get(id);
            if (contact==null){
                contact=new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setBirthDate(resultSet.getDate("birth_date"));
                contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
                map.put(id,contact);
            }

            Long contactTelDetailId= resultSet.getLong("contact_tel_id");

            if (contactTelDetailId>0){
                ContactTelDetail contactTelDetail=new ContactTelDetail();
                contactTelDetail.setId(contactTelDetailId);
                contactTelDetail.setContactId(id);
                contactTelDetail.setTelType(resultSet.getString("tel_type"));
                contactTelDetail.setTelNumber(resultSet.getString("tel_number"));
                contact.getContactTelDetails().add(contactTelDetail);
            }
        }

        return new ArrayList<Contact>(map.values());
    }
}
