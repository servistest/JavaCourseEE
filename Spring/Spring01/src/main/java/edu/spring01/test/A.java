package edu.spring01.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Created by Admin on 23.08.2016.
 */
@Component
public class A {
    private B b;
    private C c;
    public A(){
        System.out.println("Create class A " +this);
    }

    public B getB() {
        return b;
    }

//    @Autowired
//    or
//    @Inject
    @Resource(name = "b")
    public void setB(B b) {
        System.out.println("set A  parameter B " +b);
        this.b = b;
    }

    public C getC() {
        return c;
    }



//    @Inject
//    or
//     @Autowired
//    or

    @Resource(name = "c")
    public void setC(C c) {
        System.out.println("set A  parameter C " +c);
        this.c = c;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("edu.spring01.test.A{");
        sb.append("b=").append(b);
        sb.append(", c=").append(c);
        sb.append('}');
        return sb.toString();
    }
}
