package edu.aop.stopwatch;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StopWatch;

/**
 * Created by Admin on 24.11.2016.
 */
public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        StopWatch stopWatch=new StopWatch();
        stopWatch.start(methodInvocation.getMethod().getName());
        Object returnValue =methodInvocation.proceed();
        stopWatch.stop();
        dumpInfo(methodInvocation,stopWatch.getTotalTimeMillis());
        return returnValue;
    }

    void dumpInfo(MethodInvocation methodInvocation,Long totalTime ){
        System.out.println("Class name =  "+methodInvocation.getClass());
        System.out.println("Method name = "+methodInvocation.getMethod().getName());
        Object[] listParameters=methodInvocation.getArguments();
        System.out.println("All arguments =");
        for (int i = 0; i < listParameters.length; i++) {
            System.out.println("param"+i+"="+listParameters[i]);
        }
        System.out.println("Total work time  = " +totalTime + " millisecond");
    }
}
