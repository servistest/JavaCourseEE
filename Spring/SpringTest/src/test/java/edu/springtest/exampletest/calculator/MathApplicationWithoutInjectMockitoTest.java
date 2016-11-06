package edu.springtest.exampletest.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Admin on 04.11.2016.
 */
//обязательно прописывать рунера - иначе тест не использует аннотации для  инжекта @Spy, @Mock  и т.д.
//  ошибка будет :
//    org.mockito.exceptions.misusing.NotAMockException:
//            Argument passed to when() is not a mock!
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationWithoutInjectMockitoTest {
    private  MathApplication mathApplication;
    private  CalculatorService calculatorService;

    @Before
    public  void setUp(){
        System.out.println("In setUp..");
        mathApplication=new MathApplication();
        calculatorService=mock(CalculatorService.class);
        mathApplication.setCalculatorService(calculatorService);
    }

    @Test
    public void Add_VariousNumber_ReturnTrue(){
        System.out.println("In Test..");
        when(calculatorService.add(10,15)).thenReturn(25.0);
        assertEquals(25d,mathApplication.add(10,15d),1e-10);

        //проверка, вызывался ли  calculatorService.add(10.0, 15) один раз
        verify(calculatorService,times(1)).add(10.0, 15);

        //check if add function is called minimum 2 times
        verify(calculatorService, atLeast(1)).add(10.0, 15.0);

        //check if add function is called maximum 3 times
        verify(calculatorService, atMost(2)).add(10.0,15.0);
    }
}
