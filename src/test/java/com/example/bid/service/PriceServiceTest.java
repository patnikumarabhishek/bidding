package com.example.bid.service;

import com.example.bid.data.PriceData;
import com.example.bid.processor.PriceProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceServiceTest {
    private PriceService priceService;
    @Mock
    private PriceProcessor priceProcessor;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.priceService = new PriceService(priceProcessor);
    }

    @Test
    public void testWritePriceData_noError() {
        Mockito.doCallRealMethod().when(priceProcessor).write();
        List<PriceData> priceData = priceService.writePriceData();
        assert priceData.size() == 5;
    }

    @Test
    public void testWritePriceData_Exception() {
        Mockito.doReturn(null).when(priceProcessor).write();
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> priceService.writePriceData(),
                "Expected doThing() to throw, but it didn't"
        );
        assert "Cannot invoke \"java.util.Map.forEach(java.util.function.BiConsumer)\" because \"concurrentMap\" is null"
                .equals(thrown.getMessage());

    }
}
