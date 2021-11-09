package com.sevak.products.restcontrollers;


import com.sevak.products.models.Products;
import com.sevak.products.repositorys.ProductRepository;
import com.sevak.products.repositorys.ShoppingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
//@EnableEurekaClient
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository, ShoppingRepository shoppingRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Products> getAllProducts(){

        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Products getAllProducts1(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }

}
