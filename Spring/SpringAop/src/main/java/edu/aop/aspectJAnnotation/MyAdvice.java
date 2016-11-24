package edu.aop.aspectJAnnotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Admin on 24.11.2016.
 */
@Aspect
public class MyAdvice {
//    fooExecution (int intValue)) определяет
//    срез для выполнения методов foo* () с целочисленным аргументом
//    внутри всех классов из пакета edu.aop..foo, и этот аргумент (intValue) будет также передан в совет.
    @Pointcut("execution(*edu.aop..foo*(int)) && args(intValue)")
    public void fooExecution(){
    }
//    срез, предназначенный для выполнения всех методов из бинов Spring, имена которых имеют префикс  myDependency
    @Pointcut("bean(myDependency*)")
    public void inMyDependency(){}

}
