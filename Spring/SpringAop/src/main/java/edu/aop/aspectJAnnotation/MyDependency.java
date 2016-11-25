package edu.aop.aspectJAnnotation;

import org.springframework.stereotype.Component;

/**
 * Created by Admin on 24.11.2016.
 */
@Component(value = "myDependency")
public class MyDependency {
    public void foo(int intValue){
        System.out.println("foo(int) " + intValue);
    }
    public void bar(){
        System.out.println("bar() " );
    }


}
