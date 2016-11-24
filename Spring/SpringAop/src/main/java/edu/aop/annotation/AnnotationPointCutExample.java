package edu.aop.annotation;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * Created by Admin on 24.11.2016.
 */
public class AnnotationPointCutExample {
    public static void main(String[] args) {
        SampleAnnotationBean sampleAnnotationBean=new SampleAnnotationBean();
        AnnotationMatchingPointcut annotationMatchingPointcut=AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor=new DefaultPointcutAdvisor(annotationMatchingPointcut,new SimpleAdvice());
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(sampleAnnotationBean);
        SampleAnnotationBean proxySampleAnnotationBean=(SampleAnnotationBean)proxyFactory.getProxy();
        proxySampleAnnotationBean.writeMessage();
        proxySampleAnnotationBean.writeMessage2();
    }
}
