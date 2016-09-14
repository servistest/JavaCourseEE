package edu.spring01.aop.interceptor;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by Admin on 05.09.2016.
 */
public class HelloWorldAOP {
    public static void main(String[] args) {
        MessageWriter target=new MessageWriter();
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.addAdvice(new MessageDecorator());
        proxyFactory.setTarget(target);

        MessageWriter proxy=(MessageWriter) proxyFactory.getProxy();
        target.writeMessage();

        System.out.println("");
        proxy.writeMessage();
    }
}
