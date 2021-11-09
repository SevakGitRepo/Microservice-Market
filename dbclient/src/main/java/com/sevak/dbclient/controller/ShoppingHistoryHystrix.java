package com.sevak.dbclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sevak.dbclient.form.ShoppingForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ShoppingHistoryHystrix {
    @HystrixCommand(fallbackMethod = "fallback")
    public ResponseEntity<ShoppingForm[]> answer(Authentication auth){
        return new RestTemplate().getForEntity("http://localhost:8082/shopping/"+auth.getName(), ShoppingForm[].class );
    }

    private ResponseEntity<ShoppingForm[]> fallback(Authentication auth){
        return null;
    }



}
