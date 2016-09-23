package edu.springjpa.audit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 20.09.2016.
 */

public interface UserRepository extends CrudRepository<User,Long> {
}
