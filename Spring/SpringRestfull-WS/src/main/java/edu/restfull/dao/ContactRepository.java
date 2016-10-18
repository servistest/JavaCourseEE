package edu.restfull.dao;


import edu.restfull.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * Created by Admin on 19.09.2016.
 */

public interface ContactRepository extends CrudRepository<Contact,Long> {

}
