//package com.sevak.main.controller;//package com.sevak.security.restcontroller;
//
//
//import com.sevak.security.form.ProductForm;
//import com.sevak.security.models.Developer;
//import com.sevak.security.models.Products;
//
//
////import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//@RestController
//@RequestMapping("/user")
//
//public class UserRestController {
//
////    @Bean
////    @LoadBalanced
////    public RestTemplate getRestTemplate(){
////        return new RestTemplate();
////    }
//
////    @Autowired
////    private RestTemplate restTemplate;
//
////    @Autowired
////    private ProductCaller productCaller;
//
//
//
//    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('developers:read')")
//    public List<Products> getAll(){
//
// //       return productCaller.getAll();
////        //Eureka localhosti tex@ anun@---products
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Products[]> responseEntity = restTemplate.getForEntity("http://localhost:8082/product/all",
//                Products[].class);
//        System.out.println();
//
//        return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
//    }
//
//
//
////    @GetMapping("/{id}")
////    @PreAuthorize("hasAuthority('developers:read')")
////    public Products getById(@PathVariable Long id){
////                                         //Eureka localhosti tex@ anun@---products bayc LoadBalance a Petq RestTemoplate in
////        return productCaller.takeProductById(id);
////        //restTemplate.getForObject("http://products/product/"+id, Products.class);
////    }
//
////      chi ashxatum vortev histrix@ bean i mejic petqa kanchi fallback@ histrix@ ashxatuma AOP ov u chi karum
////      dra hamar hanum en mejic nayi ProductCaller
////    @HystrixCommand(fallbackMethod = "fallBackId")
////    public Products takeProductById(Long id){
////       return restTemplate.getForObject("http://products/product/"+id, Products.class);
////    }
////
////    public Products fallBackId(Long id) {
////        System.out.println("bla");
////        return null;//new Products();
////    }
////arevtur
////    @Autowired
////    private ProxyService proxyService;
//
//    @PostMapping("/buy")
//    @PreAuthorize("hasAuthority('developers:read')")
//    public ResponseEntity<Object> buyProduct(@RequestBody Map<Long, Integer> productsMap, Authentication auth) {
//        if(auth==null){
//            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
//        }
//        String email = auth.getName();
//        ProductForm productForm = new ProductForm(productsMap, email);
//                ResponseEntity<Object> responseEntity =
//                new RestTemplate().postForEntity("http://localhost:8082/product/buy", productForm, Object.class
//                        );
//
//        if(responseEntity.getStatusCode().is2xxSuccessful()) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        }else
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//
//    }
//
//        List<Developer> developers = Stream.of(new Developer(1L, "Sevak", "Tovmasyan"),
//            new Developer(2L, "Sevan", "Tovmasyan"),
//            new Developer(3L, "Lilit", "Tovmasyan"))
//            .collect(Collectors.toList());
//
//   // Karanq sarqenq vor admin@ jnji
//    @GetMapping
//    @PreAuthorize("hasAuthority('developers:read')")
//    public List<Developer> allDev(){
//
//        return developers;
//    }
//
//    @PostMapping
//    @PreAuthorize("hasAuthority('developers:write')")
//    public Developer create(@RequestBody Developer developer){
//        developers.add(developer);
//        return developer;
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('developers:write')")
//    public void deleteById(@PathVariable Long id){
//        developers.removeIf(developer -> developer.getId().equals(id));
//    }
//
//}
//
