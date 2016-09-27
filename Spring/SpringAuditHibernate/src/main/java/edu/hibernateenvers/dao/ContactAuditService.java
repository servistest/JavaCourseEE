package edu.hibernateenvers.dao;


import edu.hibernateenvers.model.ContactAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Admin on 19.09.2016.
 */
public interface ContactAuditService {
    ContactAudit findById(Long id);
    ContactAudit save(ContactAudit contactAudit);
    List<ContactAudit> findAll();
    List<ContactAudit> findByFirstName(String firstName);
    List<ContactAudit> findByFirstAndLastName(String firstName, String lastName);
    List<ContactAudit> findByMyName(String firstName);
    List<ContactAudit> findByMyNameLike(String firstNameLike);
    Page<ContactAudit> findByLastName(String lastName, Pageable pageable);
    List<ContactAudit> findByNameAndSort(String firstNameLike, Sort sort);
    List<ContactAudit> findByFirstNameOrLastName(String firstName, String lastName);;
    ContactAudit findAuditByRevision(Long id, int revision);

}
