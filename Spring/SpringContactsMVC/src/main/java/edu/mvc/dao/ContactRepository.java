package edu.mvc.dao;

import edu.mvc.model.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ALex on 13.11.2016.
 */
public interface ContactRepository  extends CrudRepository<Contact,Long>{
}
