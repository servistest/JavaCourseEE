package edu.springtest.exampletest.math;

import edu.springtest.exampletest.math.Operation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Admin on 04.11.2016.
 */
@RunWith(Parameterized.class)
public class Operation2Test {
    private Operation operation;
    private int a;
    private int b;
    private int result;

    @BeforeClass
    public static void beforeClass(){
        System.out.println("In before class...");
    }

    @Before
    public void init(){
        operation=new Operation();
        System.out.println("In  init()... ");
    }
    public  Operation2Test(int a,int b, int result){
        System.out.println("In constructor...");
        this.a=a;
        this.b=b;
        this.result=result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        System.out.println("In create parameters ...");
        Object[][] data= new Object[][]{{3,5,8},{20,30,50},{50,50,100}};
        return Arrays.asList( data);
    }

    @Test
    public void Addition_VariousNumber_ReturnEqualsTrue(){
        System.out.println(" In @Test start...");
        assertEquals(result,operation.addition(a,b));
    }

    @Test(expected = ArithmeticException.class)
    public void Div_NullNumber_ReturnArithmeticException(){
        operation.div(10,0);
    }
}
