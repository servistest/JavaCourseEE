package edu.spring01.aop.interceptor.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Admin on 06.09.2016.
 */
public class WorkerInterfaceTest  {

    public static void execute(WorkerInterface worker){
        worker.doSomeWork();
    }

    public static void main(String[] args) {
        execute(new WorkerInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Work call from anonim class ...");
            }
        });

        execute( () -> System.out.println("Work call from lambda"));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        for(Integer n: list) {
            System.out.println(n);
        }

        list.forEach(n -> {
            System.out.println(n);
        });
    }




}
