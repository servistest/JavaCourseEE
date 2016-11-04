package edu.springtest.exampletest.math;

/**
 * Created by Admin on 01.11.2016.
 */
public class Operation {

    public  int multiply(int a,int b){
        return a*b;
    }
    public int addition(int a,int b){
        if (a>100 || b>200 ){
            throw new IllegalArgumentException("Parameters must a < 100 and  b < 200 ");
        }
        return a+b;
    }
    public int div(int a, int b){
        return a/b;
    }
}
