package edu.springjpa.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

/**
 * Created by Admin on 20.09.2016.
 */
public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        User user=new User();
        user.setName("admin");
        return user;
    }
}
