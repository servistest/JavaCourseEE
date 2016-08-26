package edu.spring01.singleton;

/**
 * Created by Admin on 24.08.2016.
 */
public class Singleton {
    private static Singleton instance =new Singleton();
    private Singleton(){}
    public static  Singleton getInstance(){
            return instance;
    }

    public static void main(String[] args) {
        Singleton exampleSingleton1=Singleton.getInstance();
        System.out.println("Singleton1 = " + exampleSingleton1);
        Singleton exampleSingleton2=Singleton.getInstance();
        System.out.println("Singleton2 = " + exampleSingleton2);


    }
}
