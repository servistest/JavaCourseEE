package edu.aop.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by Admin on 24.11.2016.
 */
public class HelloWorldAopExample {
//    Logger log = LoggerFactory.getLogger(HelloWorldAopExample.class);
    public static void main(String[] args) {
        MessageWriter messageWriter=new MessageWriter();
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.setTarget(messageWriter);
        proxyFactory.addAdvice(new MessageDecorator());
        MessageWriter proxy=(MessageWriter)proxyFactory.getProxy();
        System.out.println("Write target object :");
        messageWriter.writeMessage();
        System.out.println();
        System.out.println("Write proxy :");
        proxy.writeMessage();
        System.out.println("Write proxy :");
        proxy.writeMessage2();

    }
}
