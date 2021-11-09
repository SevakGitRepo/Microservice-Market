package com.sevak.products.repositorys;


import com.sevak.products.models.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
    List<Shopping> findAllByEmail(String email);
}
