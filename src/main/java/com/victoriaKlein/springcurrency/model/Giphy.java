package com.victoriaKlein.springcurrency.model;

import java.util.List;
//giphy-model used by feign-client preparing data for thymeleaf
public class Giphy {
    private List<Giphy> data;

    public List<Giphy> getData() {
        return data;
    }

    public void setData(List<Giphy> data) {
        this.data = data;
    }

    public String getEmbed_url() {
        return embed_url;
    }

    public void setEmbed_url(String embed_url) {
        this.embed_url = embed_url;
    }

    private String embed_url;

}
