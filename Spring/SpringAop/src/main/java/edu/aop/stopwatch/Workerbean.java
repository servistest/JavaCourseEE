package edu.aop.stopwatch;

/**
 * Created by Admin on 24.11.2016.
 */
public class WorkerBean {
    void doSomeWork(Integer number){
        for (int i = 0; i < number; i++) {
            doWork();
        }
    }
    void doWork(){
        System.out.print("");
    }
}
