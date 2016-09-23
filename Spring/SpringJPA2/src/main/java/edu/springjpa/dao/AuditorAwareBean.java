package edu.springjpa.dao;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by Admin on 19.09.2016.
 */
public class AuditorAwareBean implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return "admin";
    }
}
