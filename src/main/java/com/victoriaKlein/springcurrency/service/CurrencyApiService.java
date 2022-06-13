package com.victoriaKlein.springcurrency.service;

import com.victoriaKlein.springcurrency.model.Currency;

import java.util.List;

//a layer with params for FeignClient
public interface CurrencyApiService {
    Currency currentlyCurrencyList(String appId);
    Currency historicalCurrencyList(String time, String appId);

}
