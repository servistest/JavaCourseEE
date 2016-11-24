package edu.aop.aspectJAnnotation;

/**
 * Created by Admin on 24.11.2016.
 */
public class MyBean {
    private MyDependency myDependency;

    public void execute(){
        myDependency.foo(100);
        myDependency.foo(101);
        myDependency.bar();
    }

    void setMyDependency(MyDependency myDependency){
        this.myDependency=myDependency;
    }
}
