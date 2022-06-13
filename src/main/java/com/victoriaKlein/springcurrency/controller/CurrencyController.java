package com.victoriaKlein.springcurrency.controller;


import com.victoriaKlein.springcurrency.feignClient.CurrencyClient;
import com.victoriaKlein.springcurrency.model.Currency;
import com.victoriaKlein.springcurrency.service.CurrencyService;
import com.victoriaKlein.springcurrency.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.awt.*;

@Controller
@RequestMapping("/currencies")
@EnableFeignClients(basePackageClasses = CurrencyClient.class)
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;


    @GetMapping()
    public String showAll(Model model, Currency currency) {
        currency = currencyService.getCurrent();
        model.addAttribute("currentCurrency", currency);
        model.addAttribute("updateTime", DateFormat.getActualPublishDate(currency.getTimestamp()));
        return "index.html";
    }

    @PostMapping("/resultGif")
    public String processForm(Model model, Currency currency) {
        currencyService.getPrev();
        model.addAttribute("result", currencyService.chooseExchangeCurrency(currency.getCurrencySymbol()));
        return "show.html";
    }

}
