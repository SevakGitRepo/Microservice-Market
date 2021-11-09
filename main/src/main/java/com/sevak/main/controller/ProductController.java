package com.sevak.main.controller;

import com.sevak.main.models.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductCaller productCaller;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<Products> getAll() {

        return productCaller.getAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Products getById(@PathVariable Long id) {

        return productCaller.takeProductById(id);
    }
}
