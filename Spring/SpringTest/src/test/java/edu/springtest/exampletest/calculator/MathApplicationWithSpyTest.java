package edu.springtest.exampletest.calculator;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;

/**
 * Created by Admin on 04.11.2016.
 */
public class MathApplicationWithSpyTest {
    @Spy
    Calculator calculator;
    @InjectMocks
    CalculatorService calculatorService;
    @InjectMocks
    MathApplication mathApplication;

    @Test
    public void Add_VariousNumber_ReturnTrue(){
        assertEquals(30,mathApplication.add(10,20),1e-10);
    }
}
