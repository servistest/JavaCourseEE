package edu.aop.stopwatch;

import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by Admin on 24.11.2016.
 */
public class StopWatchExample {
    public static void main(String[] args) {
        WorkerBean workerBean=new WorkerBean();
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.setTarget(workerBean);
        proxyFactory.addAdvice(new ProfilingInterceptor());
        WorkerBean proxyWorkerBean= (WorkerBean) proxyFactory.getProxy();
        proxyWorkerBean.doSomeWork(10000);

    }
}
