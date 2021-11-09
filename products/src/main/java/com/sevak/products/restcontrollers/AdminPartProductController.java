package com.sevak.products.restcontrollers;

import com.sevak.products.models.Products;
import com.sevak.products.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Securitin drats chi
@RestController
@RequestMapping("/product/admin")
public class AdminPartProductController {

    @Autowired
    private ProductRepository productRepository;
    //security chka stex
    //updaten el mejna
    @PostMapping(value = "/add")
    public ResponseEntity<?> setProducts(@RequestBody Products product){ //@VALUE PTI LINI
        Products currentProduct =productRepository.findByProductName(product.getProductName()).orElse(null);
        try{
            if(currentProduct != null){
                currentProduct.setProductCount(currentProduct.getProductCount()+product.getProductCount());
                currentProduct.setProductPrice(product.getProductPrice());
                productRepository.save(currentProduct);
            }else {

                productRepository.save(product);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> setProducts(@PathVariable Long id){ //@VALUE PTI LINI

        try{
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
