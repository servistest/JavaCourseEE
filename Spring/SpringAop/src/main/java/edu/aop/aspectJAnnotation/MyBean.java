package edu.aop.aspectJAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Admin on 24.11.2016.
 */
@Component(value = "myBean")
public class MyBean {
    private MyDependency myDependency;

    public void execute(){
        myDependency.foo(100);
        myDependency.foo(101);
        myDependency.bar();
    }
    @Autowired
    void setMyDependency(MyDependency myDependency){
        this.myDependency=myDependency;
    }
}
