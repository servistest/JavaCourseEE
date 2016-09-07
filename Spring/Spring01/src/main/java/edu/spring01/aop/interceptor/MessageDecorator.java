package edu.spring01.aop.interceptor;

import org.aopalliance.intercept.MethodInvocation;


/**
 * Created by Admin on 05.09.2016.
 */
class MessageDecorator implements org.aopalliance.intercept.MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.print("Hello ");
        Object obj=methodInvocation.proceed();
        System.out.println("!");
        return obj;
    }
}
