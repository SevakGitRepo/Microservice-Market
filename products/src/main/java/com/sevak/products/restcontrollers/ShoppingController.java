package com.sevak.products.restcontrollers;

import com.sevak.products.form.ShoppingForm;
import com.sevak.products.models.Shopping;
import com.sevak.products.repositorys.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @GetMapping("/{email}")
    public List<ShoppingForm> getAllShoppingByUser(@PathVariable String email){

        return  shoppingRepository.findAllByEmail(email).stream()
                .map(s->new ShoppingForm(s.getProductName(),
                        s.getProductCount(),
                        s.getProductPrice(),
                        s.getDate()))
                .collect(Collectors.toList());
    }

}

