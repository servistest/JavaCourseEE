package edu.springjpa.audit;

import java.util.List;

/**
 * Created by Admin on 20.09.2016.
 */
public interface UserService {
     User getCurrentUser(Long id);
     List<User> findAllUser();
     User save(User user);
}
