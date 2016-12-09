package edu.mvc.dao;

import edu.mvc.model.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Admin on 09.12.2016.
 */
public interface ContactRepository extends PagingAndSortingRepository<Contact,Long>{
}
