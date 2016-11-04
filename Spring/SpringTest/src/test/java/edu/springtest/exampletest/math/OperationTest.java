package edu.springtest.exampletest.math;

import edu.springtest.exampletest.math.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Admin on 01.11.2016.
 */
@RunWith(Parameterized.class)
public class OperationTest {
    private Operation operation= new Operation();
    @Parameterized.Parameter(0)
    public int a;
    @Parameterized.Parameter(1)
    public int b;
    @Parameterized.Parameter(2)
    public int result;


//   или через конструктор присваиваем параметры (только параметры дожны быть public)
//    public OperationTest(int a, int b,int result){
//        this.a=a;
//        this.b=b;
//        this.result=result;
//    }
    @Parameters
    public static Collection<Object[]> data(){
        Object[][] data=new Object[][]{{2,4,6},{8,15,23},{40,50,90}};
        return Arrays.asList(data);
    }

    @Test
    public void Addition_ParametersNumber_ReturnEqualsTrue(){
        assertEquals(result,operation.addition(a,b));
    }

    @Test
    public void Multiply_VariousNumber_ReturnEqualsTrue(){
        assertEquals(a*b,operation.multiply(a,b));
    }


    @Test(expected = IllegalArgumentException.class)
    public void Addition_VariousFaultsArgument_ReturnIllegalException(){
        operation.addition(200,13);
    }
}
