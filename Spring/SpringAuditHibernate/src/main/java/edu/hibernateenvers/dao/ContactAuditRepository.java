package edu.hibernateenvers.dao;


import edu.hibernateenvers.model.ContactAudit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Admin on 19.09.2016.
 */

public interface    ContactAuditRepository extends CrudRepository<ContactAudit,Long> {

    List<ContactAudit> findByFirstName(String firstName);
    List<ContactAudit> findByFirstNameAndLastName(String firstName, String lastName);


}
