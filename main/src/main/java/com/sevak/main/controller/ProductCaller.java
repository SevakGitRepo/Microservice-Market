package com.sevak.main.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sevak.main.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.Objects;

@Component
public class ProductCaller {
    private final RestTemplate restTemplate=new RestTemplate();



    @HystrixCommand(fallbackMethod = "fallBackId")
    public Products takeProductById(Long id){
        return restTemplate.getForObject("http://localhost:8082/product/"+id, Products.class);
    }

    public Products fallBackId(Long id) {
        return new Products("No Product", 0, 0);
    }

    @HystrixCommand(fallbackMethod = "fallBackAll")
    public List<Products> getAll(){
        ResponseEntity<Products[]> responseEntity = restTemplate.getForEntity("http://localhost:8082/product/all",
                Products[].class);


        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
    }

    public List<Products> fallBackAll(){
        return Collections.singletonList(new Products("No Product", 0, 0));
    }
}
