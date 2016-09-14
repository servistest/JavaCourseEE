package edu.spring01.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Admin on 23.08.2016.
 */
@Configuration
@ComponentScan(basePackages = "edu.spring01.test")
public class MainConfiguration {

    @Bean
    public B b(){
        return new B();
    }

    @Bean
    public C c(){
        return new C();
    }

    @Bean
    public A a(){
        return new A();
    }

}
