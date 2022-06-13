package com.victoriaKlein.springcurrency.service;

import com.victoriaKlein.springcurrency.configuration.CurrencyAppConfig;
import com.victoriaKlein.springcurrency.model.Currency;
import com.victoriaKlein.springcurrency.model.Giphy;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class CurrencyServiceTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @MockBean
    CurrencyApiService currencyApiService;
    @MockBean
    GiphyApiService giphyApiService;

    private Currency currentCurrency;
    private Currency prevCurrency;
    private Map<String, String> comparisonResult;

    public void init() {
        currentCurrency = new Currency();
        Map<String, Double> currentRatesMap = new HashMap<>();
        currentRatesMap.put("A", 1.0);
        currentRatesMap.put("B", 2.0);
        currentRatesMap.put("C", 3.0);
        currentCurrency.setRates(currentRatesMap);
        prevCurrency = new Currency();
        Map<String, Double> prevRatesMap = new HashMap<>();
        prevRatesMap.put("A", 1.0);
        prevRatesMap.put("B", 2.0);
        prevRatesMap.put("C", 3.0);
        prevCurrency.setRates(prevRatesMap);
    }

    public Map<String, String> result() {
        comparisonResult = new HashMap<>();
        comparisonResult.put("greaterThan", "broke");
        comparisonResult.put("lessThan", "rich");
        comparisonResult.put("equal", "dance");
        return comparisonResult;
    }

    @Test
    void getCurrent() {
        init();
        when(currencyApiService.currentlyCurrencyList(anyString())).thenReturn(this.currentCurrency);
    }

    @Test
    void getPrev() {
        init();
        when(currencyApiService.historicalCurrencyList(anyString(), anyString())).thenReturn(this.prevCurrency);
    }

    @Test
    void chooseExchangeCurrency() {
        getCurrent();
        getPrev();
        if (currentCurrency.getRates().get("B") > prevCurrency.getRates().get("A"))
            result().get("greaterThen");
        if (currentCurrency.getRates().get("B") < prevCurrency.getRates().get("C"))
            result().get("lessThan");
        if (currentCurrency.getRates().get("A") == prevCurrency.getRates().get("A")) ;
        result().get("equal");
    }

    @Test
    void getGiphy() {
        when(giphyApiService.getGiphy(anyString(), anyString())).thenReturn(new Giphy());
        System.out.println(result());
    }
}