package com.sevak.products.repositorys;


import com.sevak.products.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByProductName(String name);
}
