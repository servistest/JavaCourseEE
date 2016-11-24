package edu.aop.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by Admin on 24.11.2016.
 */
public class SimpleAdvice  implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(" Start log method = "+methodInvocation.getMethod());
        Object o=methodInvocation.proceed();
        System.out.println(" Stop log method = "+methodInvocation.getMethod());
        return o;
    }
}
