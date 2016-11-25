package edu.aop.aspectJAnnotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Admin on 24.11.2016.
 */
@Component
@Aspect
public class MyAdvice {
//    fooExecution (int intValue)) определяет
//    срез для выполнения методов foo* () с целочисленным аргументом
//    внутри всех классов из пакета edu.aop..foo, и этот аргумент (intValue) будет также передан в совет.
    @Pointcut("execution(* edu.aop..foo*(int)) && args(intValue)")
    public void fooExecution(int intValue){ }
//    срез, предназначенный для выполнения всех методов из бинов Spring, имена которых имеют префикс  myDependency
    @Pointcut("bean(myDependency*)")
    public void inMyDependency(){
    }

//    Конструкция  "fooExecution (intValue) && inMyDependency ()" означает, что для применения
//    совета должны быть удовлетворены условия обоих срезов
    @Before("fooExecution(intValue) &&  inMyDependency()")
    public void simpleBeforeAdvice(JoinPoint joinPoint,int intValue){
//        execute, where intValue<>100
        if(intValue!=100){
            System.out.println("Execute :" + joinPoint.getSignature().getDeclaringTypeName() +" "
                    + joinPoint.getSignature().getName() + "argument" + intValue );
        }
    }

    //    Конструкция  "fooExecution (intValue) && inMyDependency ()" означает, что для применения
//    совета должны быть удовлетворены условия обоих срезов
    @Around("fooExecution(intValue) && inMyDependency()")
    public Object simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, int intValue) throws Throwable{
        //write before execute
        System.out.println( "Before execute " + proceedingJoinPoint.getSignature().getDeclaringType() + " "+
        proceedingJoinPoint.getSignature().getName() + " intValue=" + intValue);
//        execute method
        Object retValue=proceedingJoinPoint.proceed();
        System.out.println( "After execute " + proceedingJoinPoint.getSignature().getDeclaringType() + " "+
                proceedingJoinPoint.getSignature().getName() + " intValue=" + intValue);
        return retValue;
    }


}
