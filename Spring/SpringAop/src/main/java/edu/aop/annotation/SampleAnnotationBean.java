package edu.aop.annotation;

/**
 * Created by Admin on 24.11.2016.
 */
public class SampleAnnotationBean {
    void writeMessage(){
        System.out.println(" Process method1... ");
    }
    @AdviceRequired
    void writeMessage2(){
        System.out.println("Process method2... ");
    }
}
