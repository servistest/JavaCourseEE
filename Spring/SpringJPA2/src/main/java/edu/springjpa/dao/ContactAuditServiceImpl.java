package edu.springjpa.dao;


import com.google.common.collect.Lists;
import edu.springjpa.model.ContactAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Admin on 19.09.2016.
 */
@Service("contactAuditService")
@Repository
@Transactional
public class ContactAuditServiceImpl implements ContactAuditService {
    @Autowired
    private ContactAuditRepository contactAuditRepository;

    @Autowired
    private ContactJpaRepository contactJpaRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ContactAudit> findAll() {
        return Lists.newArrayList(contactAuditRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactAudit> findByFirstName(String firstName) {
        return  contactAuditRepository.findByFirstName(firstName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactAudit> findByFirstAndLastName(String firstName, String lastName) {
        return contactAuditRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<ContactAudit> findByMyName(String firstName) {
        return contactJpaRepository.findByMyName(firstName) ;
    }

    @Override
    public List<ContactAudit> findByMyNameLike(String firstNameLike) {
        return contactJpaRepository.findByMyNameLike(firstNameLike);
    }

    @Override
    public Page<ContactAudit> findByLastName(String lastName, Pageable pageable) {
        return contactJpaRepository.findByLastName(lastName,pageable);
    }

    @Override
    public List<ContactAudit> findByNameAndSort(String firstNameLike, Sort sort) {
        return contactJpaRepository.findByNameAndSort(firstNameLike,sort);
    }

    @Override
    public List<ContactAudit> findByFirstNameOrLastName(String firstName, String lastName) {
        return contactJpaRepository.findByFirstNameOrLastName(firstName,lastName);
    }

    @Transactional(readOnly = true)
    @Override
    public ContactAudit findById(Long id) {
        return contactAuditRepository.findOne(id);
    }

    @Override
    public ContactAudit save(ContactAudit contactAudit) {
        return contactAuditRepository.save(contactAudit);
    }


}
