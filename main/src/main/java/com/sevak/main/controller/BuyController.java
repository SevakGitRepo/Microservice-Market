package com.sevak.main.controller;

import com.sevak.main.excep.MyException;
import com.sevak.main.form.ProductForm;
import com.sevak.main.models.User;
import com.sevak.main.reposirory.UserRepository;
import com.sevak.main.valid.TokenValidity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/market")
public class BuyController {


    @Autowired
    private TokenValidity tokenValidity;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/buy")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<Object> buyProduct(@RequestBody Map<Long, Integer> productsMap, ServletRequest servletRequest) {
        ResponseEntity<ProductForm> responseEntity;

        //harcumic vercnum enq token@
        String token = tokenValidity.resolveToken((HttpServletRequest) servletRequest);

        //harcumic vercnum enq username
        String email = tokenValidity.getUsername(token);

        //Nayum enq toke@ valida te voch
        boolean valid = tokenValidity.validateToken(token);

        if(token==null || !valid){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }else {
           //Balance
            int amount = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new).getBalance();

            ProductForm productForm = new ProductForm(productsMap, email,
                    amount);
            try {

                responseEntity = amountUser(productForm);

            }catch (Exception e){
                responseEntity=null;
            }


        }
        //Stugum enq normala te voch
        if(responseEntity!=null&&responseEntity.getStatusCode().is2xxSuccessful()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Transactional(rollbackFor = MyException.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    ResponseEntity<ProductForm> amountUser(ProductForm productForm) throws MyException {
        User user = userRepository.findByEmail(productForm.getEmail()).orElseThrow(()->new MyException("User not found"));
        try {
            ResponseEntity<ProductForm> responseEntity =
                    restTemplate.postForEntity("http://localhost:8082/product/buy", productForm, ProductForm.class);


            int responseAmount = Objects.requireNonNull(responseEntity.getBody()).getAmount();
           if(responseAmount==0){
               throw new MyException("Response balance 0");
           }

            user.setBalance(user.getBalance() - responseAmount);

            userRepository.save(user);

        }catch (Exception e){
            throw new MyException("Rollback for example save");
        }
            return new ResponseEntity<>(HttpStatus.OK);
    }



}
