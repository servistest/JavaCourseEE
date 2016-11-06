package edu.springtest.exampletest.stock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 04.11.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PortfolioWithAnnotationTest {
    @InjectMocks
    Portfolio portfolio;
    @Mock
    StockService stockService;

    @Test
    public void MarketValue_VariousNumber_ReturnTrue(){
        Stock ibm=new Stock("1","IBM",20);
        Stock apple=new Stock("2","Apple",15);

        List<Stock> stockList=new ArrayList<>();
        stockList.add(ibm);
        stockList.add(apple);
        portfolio.setStocks(stockList);

        when(stockService.getPrice(ibm)).thenReturn(200d);
        when(stockService.getPrice(apple)).thenReturn(100d);

        assertEquals(20d*200+15*100d,portfolio.getMarketValue(),1e-5);

    }

}
