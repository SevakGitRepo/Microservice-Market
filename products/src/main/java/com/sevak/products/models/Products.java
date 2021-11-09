package com.sevak.products.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @Size(min=2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "product_name")
    private String productName;

    //@Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "product_count")
    private int productCount;

    // @Size(min=2, max = 8, message = "Size should be between 2 and 30 characters")
    @Column(name = "product_price")
    private int productPrice;

    public Products(String productName, int productCount, int productPrice) {
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }


}
