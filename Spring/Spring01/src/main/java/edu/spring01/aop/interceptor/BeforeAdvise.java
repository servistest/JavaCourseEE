package edu.spring01.aop.interceptor;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Created by Admin on 05.09.2016.
 */
public class BeforeAdvise implements MethodBeforeAdvice,AfterReturningAdvice{
    public static void main(String[] args) {
        MessageWriter target=new MessageWriter();
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.addAdvice(new BeforeAdvise());
        proxyFactory.setTarget(target);

        MessageWriter proxy=(MessageWriter) proxyFactory.getProxy();
        proxy.writeMessage();

    }

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before method:"+method.getName());
    }

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("After method: "+method.getName());
    }
}
