package edu.aop.hello;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by Admin on 24.11.2016.
 */
public class SimpleBeforeAfterAdviceExample {
    public static void main(String[] args) {
        MessageWriter target=new MessageWriter();
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.addAdvice(new SimpleAfterAdvice());
        MessageWriter proxyMessageWriter=(MessageWriter)proxyFactory.getProxy();
        proxyMessageWriter.writeMessage();
        proxyMessageWriter.writeMessage2();
    }
}
