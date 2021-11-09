package com.sevak.products.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping")
public class Shopping extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

@Column(name = "email")
    private String email;

    public Shopping(String productName, int productCount, int productPrice, LocalDate date, String email) {
        super(productName, productCount, productPrice);
        this.date = date;
        this.email = email;
    }


}
