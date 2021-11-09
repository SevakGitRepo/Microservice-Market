package com.sevak.dbclient.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingForm {

    private String productName;

    private int productCount;

    private int productPrice;

    private LocalDate date;
}
