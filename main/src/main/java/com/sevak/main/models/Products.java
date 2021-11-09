package com.sevak.main.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
        private Long id;
        private String productName;
        private int productCount;
        private int productPrice;

        public Products(String productName, int productCount, int productPrice) {
                this.productName = productName;
                this.productCount = productCount;
                this.productPrice = productPrice;
        }
}
