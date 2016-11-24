package edu.aop.aspectJAnnotation;

/**
 * Created by Admin on 24.11.2016.
 */
public class MyDependency {
    public void foo(int intValue){
        System.out.println("foo(int) " + intValue);
    }
    public void bar(){
        System.out.println("bar() " );
    }


}
