package com.victoriaKlein.springcurrency.service;

import com.victoriaKlein.springcurrency.model.Giphy;


//a layer with params for FeignClient
public interface GiphyApiService {
   Giphy getGiphy (String apiKey, String q);
}
