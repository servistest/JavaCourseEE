package edu.springtest.exampletest.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ALex on 06.11.2016.
 */
//обязательно прописывать рунера - иначе тест не использует аннотации для  инжекта @Spy, @Mock  и т.д.
//  ошибка будет :
//    org.mockito.exceptions.misusing.NotAMockException:
//            Argument passed to when() is not a mock!
@RunWith(MockitoJUnitRunner.class)
public class LinkedListTest {

    @Spy
    public List spyList=new LinkedList();

//    List list=new LinkedList();
//    List spyList= spy(list);

    @Test
    public void shouldByReturnTrue(){
//        doReturn - так правильно. Сначало указываем Что вернуть при вызове РЕАЛЬНОГО  метода в spy.
        doReturn("One").when(spyList).get(0);
//        в данном случае при вызове  when() . thenReturn () - ошибка IndexOutOfBoundsException:. Вызывается реальный метод, а
//        в экземпляре нет ни одно значения.
//        when(spyList.get(0)).thenReturn("One","Two");
        assertEquals("One",spyList.get(0));
    }


    @Test
    public void spyListTest(){
        List<String> spyList= Mockito.spy(new ArrayList<String>());
        spyList.add("One");
        spyList.add("One");
        spyList.add("Two");
        assertEquals(3,spyList.size());

        when(spyList.size()).thenReturn(20);
        assertEquals(20,spyList.size());

        verify(spyList,times(2)).add("One");
        verify(spyList,times(1)).add("Two");
        assertEquals(20,spyList.size());
    }
    @Test
    public void iteratorTest(){
        Iterator iterator=mock(Iterator.class);
        when(iterator.next()).thenReturn("Yoo").thenReturn("Hoo!");
        String result =iterator.next()+" " +iterator.next();
        assertEquals(result,"Yoo Hoo!");
    }

    @Test
    public void anyValueArgs(){
        Comparable comparable=mock(Comparable.class);
        when(comparable.compareTo(anyInt())).thenReturn(-29);
        assertEquals(comparable.compareTo(10),-29);
    }

    @Test(expected = IOException.class)
    public void testThrowException() throws IOException{
        OutputStream outputStream=mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
        doThrow(new IOException()).when(outputStream).close();
        outputStreamWriter.close();
    }

}
