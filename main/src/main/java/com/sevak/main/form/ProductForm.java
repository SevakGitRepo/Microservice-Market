package com.sevak.main.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductForm {

    private Map<Long, Integer> productsMap;
    private String email;
    private int amount;


}
