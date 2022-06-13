package com.victoriaKlein.springcurrency.feignClient;

import com.victoriaKlein.springcurrency.model.Giphy;
import com.victoriaKlein.springcurrency.service.GiphyApiService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Api calls with FeignClient
@FeignClient(name = "giphyService", url = "${giphy.url}")
public interface GiphyClient extends GiphyApiService {
    @Override
    @GetMapping()
    Giphy getGiphy(@RequestParam String apiKey, @RequestParam String q);
}
