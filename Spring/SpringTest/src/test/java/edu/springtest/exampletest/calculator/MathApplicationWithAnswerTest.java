package edu.springtest.exampletest.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by Admin on 04.11.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationWithAnswerTest {

    @Mock
    CalculatorService calculatorService;
    @InjectMocks
    MathApplication mathApplication;

    @Test
    public void Add_VariousNumber_ReturnTrue(){
        when(calculatorService.add(10,20)).thenAnswer(new Answer<Double>() {
            @Override
            public Double answer(InvocationOnMock invocationOnMock) throws Throwable {
                //get the arguments passed to mock
                Object[] args = invocationOnMock.getArguments();

                //get the mock
                Object mock = invocationOnMock.getMock();
                return 30d;
            }
        });
        assertEquals(30,mathApplication.add(10,20),1e-10);
    }



}
