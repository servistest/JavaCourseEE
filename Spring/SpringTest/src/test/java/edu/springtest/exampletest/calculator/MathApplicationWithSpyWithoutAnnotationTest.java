package edu.springtest.exampletest.calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 04.11.2016.
 */
//обязательно прописывать рунера - иначе тест не использует аннотации для  инжекта @Spy, @Mock  и т.д.
//  ошибка будет :
//    org.mockito.exceptions.misusing.NotAMockException:
//            Argument passed to when() is not a mock!
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationWithSpyWithoutAnnotationTest {
    private MathApplication mathApplication;
    private CalculatorService spyCalculator;


    @Before
    public void setUp(){
        mathApplication=new MathApplication();
        Calculator calculator=new Calculator();
        //spy   только для экземпляров объектов, а mock для классов и интерфейсов.
        // spy-объект вызывает только реальные методы!!!
        spyCalculator=spy(calculator);
        mathApplication.setCalculatorService(spyCalculator);
    }

    @Test
    public void Add_VariousNumber_ReturnTrue(){
        assertEquals(30,mathApplication.add(10,20),1e-10);
    }
}
