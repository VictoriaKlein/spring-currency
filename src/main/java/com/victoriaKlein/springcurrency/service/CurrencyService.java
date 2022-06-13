package com.victoriaKlein.springcurrency.service;

import com.victoriaKlein.springcurrency.configuration.CurrencyAppConfig;
import com.victoriaKlein.springcurrency.model.Currency;
import com.victoriaKlein.springcurrency.model.Giphy;
import com.victoriaKlein.springcurrency.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CurrencyService {

    private CurrencyAppConfig currencyAppConfig;
    private CurrencyApiService currencyApiService;
    private GiphyApiService giphyApiService;
    private Currency currentCurrency;
    private Currency prevCurrency;

    //injection in order to use currencyApiService interface to make call from controller
    // using config params from currencyAppConfig as arguments
    @Autowired
    public CurrencyService(CurrencyAppConfig currencyAppConfig,
                           CurrencyApiService currencyApiService,
                           GiphyApiService giphyApiService) {
        this.currencyAppConfig = currencyAppConfig;
        this.currencyApiService = currencyApiService;
        this.giphyApiService = giphyApiService;
    }

    //getting a List of current rates
    public Currency getCurrent() {
        currentCurrency = currencyApiService.currentlyCurrencyList(currencyAppConfig.serviceUrlAppId);
        System.out.println("today " + currentCurrency.getRates());
        return currentCurrency;
    }

    //getting a List of previous rates
    public Currency getPrev() {
        prevCurrency = currencyApiService.historicalCurrencyList(DateFormat.getPrevDate(), currencyAppConfig.serviceUrlAppId);
        System.out.println("yesterday " + prevCurrency.getRates() + DateFormat.getActualPublishDate(prevCurrency.getTimestamp()));
        return prevCurrency;
    }

    //get exchange rates: today and yesterday for comparison using argument for a key from the Controller
    public String chooseExchangeCurrency(String key) {
        Double ratesToday = currentCurrency.getRates().get(key);
        Double ratesYesterday = prevCurrency.getRates().get(key);
        String result;
        System.out.println(key + " today: " + ratesToday + " yesterday: " + ratesYesterday);
//comparison
        if (Double.compare(ratesToday, ratesYesterday) < 0) {
            System.out.println("ratesToday < ratesYesterday");
            result = getGiphy(currencyAppConfig.giphyRich);
        } else if (Double.compare(ratesToday, ratesYesterday) > 0) {
            System.out.println("ratesToday > ratesYesterday");
            result = getGiphy(currencyAppConfig.giphyBroke);
        } else {
            System.out.println("ratesToday=ratesYesterday");
            result = getGiphy(currencyAppConfig.giphyRandom);
        }
        return result;

    }

    //getting response from giphy-service
    public String getGiphy(String q) {
        Giphy giphy = giphyApiService.getGiphy(currencyAppConfig.giphyApiKey, q);
        //getting url to import to thymeleaf and HTML, for JS might be feign-response enough
        String url = "";
        for (Giphy e : giphy.getData()) {
            url = e.getEmbed_url();
        }
        return url;
    }
}
