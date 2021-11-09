package com.sevak.products.models;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Maybe used
@MappedSuperclass
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String productName;

    private int productCount;

    private int productPrice;

    public BaseEntity(String productName, int productCount, int productPrice) {
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }
}
