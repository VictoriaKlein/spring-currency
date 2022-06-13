package com.victoriaKlein.springcurrency.feignClient;

import com.victoriaKlein.springcurrency.model.Currency;
import com.victoriaKlein.springcurrency.service.CurrencyApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//Api calls with FeignClient
@FeignClient(name = "currencyService", url = "${currencyservice.url}")
public interface CurrencyClient extends CurrencyApiService {
    @Override
    @GetMapping("/historical/{time}.json?app_id={appId}")
    Currency historicalCurrencyList(@PathVariable String time, @PathVariable String appId);

    @Override
    @GetMapping("/latest.json?app_id={appId}")
    Currency currentlyCurrencyList(@PathVariable String appId);
}
