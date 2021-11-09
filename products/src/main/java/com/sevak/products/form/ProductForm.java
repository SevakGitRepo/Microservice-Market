package com.sevak.products.form;


import lombok.Data;

import java.util.Map;
@Data
public class ProductForm {
    private Map<Long, Integer> productsMap;
    private String email;
    private int amount;
}
