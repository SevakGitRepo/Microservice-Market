package com.sevak.products.restcontrollers;

import com.sevak.products.excep.MyException;
import com.sevak.products.form.ProductForm;
import com.sevak.products.models.Products;
import com.sevak.products.models.Shopping;
import com.sevak.products.repositorys.ProductRepository;
import com.sevak.products.repositorys.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductBuyController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingRepository shoppingRepository;

    @PostMapping("/buy")

    public ProductForm buyProducts(@RequestBody ProductForm productForm) throws MyException {
        int amount =0;

        amount= processBuy(productForm.getProductsMap(), productForm.getEmail(), productForm.getAmount());
        productForm.setAmount(amount);
        return productForm;

    }

    //Verevini funkcian e
    @Transactional(rollbackFor = MyException.class,// propagation = Propagation.MANDATORY,
            isolation = Isolation.SERIALIZABLE)
    int processBuy(Map<Long, Integer> products, String email, int amount) throws MyException {
        int currentAmount=0;

            if(products.isEmpty()){
                return  0;
            } else {

                //Tranzakcia

                for (Map.Entry<Long, Integer> map : products.entrySet()) {
                    Products currentProducts = productRepository.findById(map.getKey())
                            .orElseThrow(()-> new MyException("Product id not found"));
                    if(map.getValue()>currentProducts.getProductCount()){
                        throw new MyException("No enough product");
                    }
                    else {
                        //hashvum enq @ndhanur arjeq@
                        currentAmount += map.getValue()*currentProducts.getProductPrice();
                        //Lracnum enq Shopping
                        shoppingRepository.save(new Shopping(currentProducts.getProductName(),
                                map.getValue(),
                                currentProducts.getProductPrice(),
                                LocalDate.now(),
                                email));
                        currentProducts.setProductCount(currentProducts.getProductCount()-map.getValue());
                        productRepository.save(currentProducts);
                    }
                }
                //stugum enq vor pox@ shat chlini
                if(currentAmount>amount){
                    throw new MyException("No enough money");
                }

            }

        return currentAmount;
    }
}
