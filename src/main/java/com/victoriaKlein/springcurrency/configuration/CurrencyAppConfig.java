package com.victoriaKlein.springcurrency.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyAppConfig {

    @Value("${currencyservice.url}")
    public String serviceUrl;
    @Value("${currencyservice.url.app.id}")
    public String serviceUrlAppId;
    @Value("${currencyservice.base}")
    String baseCurCompareTo;
    public @Value("${currencyservice.rate}")
    String rateCurCompareWith;
    public @Value("${giphy.url}")
    String giphyUrl;
    public @Value("${giphy.api.key}")
    String giphyApiKey;
    public @Value("${giphy.rich}")
    String giphyRich;
    public @Value("${giphy.broke}")
    String giphyBroke;
    public @Value("${giphy.random}")
    String giphyRandom;


}
