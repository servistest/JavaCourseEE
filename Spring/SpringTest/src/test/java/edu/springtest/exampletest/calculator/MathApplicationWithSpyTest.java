package edu.springtest.exampletest.calculator;

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
public class MathApplicationWithSpyTest {

    //создание spy-объекта. @Spy заменяет CalculatorService calculatorService= spy(new Calculator());
    @Spy
    CalculatorService spyCalculator=new Calculator();
    // Создание и внедрение экземпляра класса, который будем  тестировать плюс внедрение  в него spy spyCalculator. @InjectMocks заменяет команды:
//    MathApplication mathApplication=new MathApplication();
//    mathApplication.setCalculatorService(spyCalculator);
    @InjectMocks
    MathApplication mathApplication;

    @Test
    public void Add_VariousNumber_ReturnTrue(){

        assertEquals(30d,mathApplication.add(10,20),1e-10);
    }
}
