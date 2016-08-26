package edu.spring01.singleton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Admin on 25.08.2016.
 */
@Configuration
@ComponentScan(basePackages = "edu.spring01.singleton")
@PropertySource(value = "classpath:AutoConfig.properties")
public class CaseAutoConfig {
    @Value("${auto.mame}")
    private String name;
    @Value("${auto.price}")
    private Double price;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.spring01.singleton.CaseAutoConfig{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
