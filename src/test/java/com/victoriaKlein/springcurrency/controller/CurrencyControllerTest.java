package com.victoriaKlein.springcurrency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victoriaKlein.springcurrency.SpringCurrencyApplication;
import com.victoriaKlein.springcurrency.model.Currency;
import com.victoriaKlein.springcurrency.model.Giphy;
import com.victoriaKlein.springcurrency.service.CurrencyService;
import com.victoriaKlein.springcurrency.util.DateFormat;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @MockBean
    private CurrencyService currencyService;
    @Autowired
    MockMvc mockMvc;
    Currency currency;

    public void initCurrency() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("RUB", 58.00001);
        currency = new Currency(1655060405, "USD", rates);
    }

    @Test
    void showAll() throws Exception {
        initCurrency();
        when(currencyService.getCurrent()).thenReturn(currency);
        assertEquals(1655060405, currency.getTimestamp());
        assertEquals("Sun Jun 12 22:00:05 MSK 2022", DateFormat.getActualPublishDate(1655060405));
        mockMvc.perform(get("/currencies")).andExpect(status().isOk())
                .andExpect(view().name("index.html"))
                .andExpect(model().attribute("currentCurrency", instanceOf(Currency.class)))
                .andExpect(model().attribute("updateTime", DateFormat.getActualPublishDate(currency.getTimestamp())));
    }

    @Test
    void processForm() throws Exception {
        initCurrency();
        when(currencyService.getPrev()).thenReturn(currency);
        currency.setCurrencySymbol(currency.getRates().keySet().stream().filter(key -> key.equals("RUB")).findAny().get());
        assertEquals("RUB", currency.getCurrencySymbol());
        Giphy giphy = new Giphy();
        giphy.setEmbed_url("url");
        when(currencyService.chooseExchangeCurrency(currency.getCurrencySymbol())).thenReturn(giphy.getEmbed_url());
        mockMvc.perform(post("/currencies/resultGif")).andExpect(status().isOk());
    }
}