package edu.springjpa.audit;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 20.09.2016.
 */
@Repository
@Service("currentUserService")
public class CurrentUserService implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getCurrentUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAllUser() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
