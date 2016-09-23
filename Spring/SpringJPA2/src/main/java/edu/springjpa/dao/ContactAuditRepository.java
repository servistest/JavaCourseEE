package edu.springjpa.dao;

import edu.springjpa.model.ContactAudit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 19.09.2016.
 */

public interface    ContactAuditRepository extends CrudRepository<ContactAudit,Long> {

    List<ContactAudit> findByFirstName(String firstName);
    List<ContactAudit> findByFirstNameAndLastName(String firstName, String lastName);


}
