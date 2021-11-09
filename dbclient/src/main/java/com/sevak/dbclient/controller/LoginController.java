package com.sevak.dbclient.controller;
import com.sevak.dbclient.form.ShoppingForm;
import com.sevak.dbclient.models.Role;
import com.sevak.dbclient.models.Status;
import com.sevak.dbclient.models.User;
import com.sevak.dbclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/success")
    public String successPage(Authentication auth, Model model){
        User user = userRepository.findByEmail(auth.getName()).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("currentUser", user);
        model.addAttribute("admin",auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")));
            return "success";
    }

    @Autowired
    private  ShoppingHistoryHystrix shoppingHistoryHystrix;

    //get history
    @GetMapping("/success/history")
    public String historyPage(Authentication auth, Model model){

        ResponseEntity <ShoppingForm[]> list = shoppingHistoryHystrix.answer(auth);

        if(list==null){
            return "db_not_answer";
        }
        model.addAttribute("history", Objects.requireNonNull(list.getBody()));
        return "history";
    }

    //LOGIN
    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model){
        if(request.getParameterMap().containsKey("error")){
            model.addAttribute("error", true);
        }
        return "login";
    }

    //REGISTRY
    @GetMapping("/registry")
    public String registry(){
        return "registry";
    }
    @PostMapping("/registry")
    public String registryPost(@RequestParam("name") String name, @RequestParam("surname") String surname,
                               @RequestParam("email") String email, @RequestParam("password") String password, Model model){

        User user = new User(name, surname, passwordEncoder.encode(password), email, Role.USER, Status.ACTIVE);

        for (User user1 : userRepository.findAll()) {
            if(user.getEmail().equals(user1.getEmail()))
            {
                model.addAttribute("isExist", true);
                return "registry";
            }
        }
        userRepository.save(user);
        return "ok";
    }

    //UPDATE
    @GetMapping("/update")
    public String update(){
        return "update";
    }
    @PostMapping("/update")
    public String updatePost(@RequestParam("email") String email, @RequestParam("status") String status,
                             @RequestParam("balance") String balance){

        //User user = new User(name, surname, password, email);

        try {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setBalance(user.getBalance()+Integer.parseInt(balance));
        user.setStatus(Status.valueOf(status));

        userRepository.save(user);
                return "all_user";

        }catch (Exception e) {
            return "update";
        }
    }

    //GetALL
    @GetMapping("/allUser")
    public String signAllUsers(){
//        List<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
        return "all_user";
    }
    @PostMapping("/allUser")
    public String signAllUsersPost(){
//        List<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
        return "all_user";
    }

    //DELETE
    @GetMapping("/delete")
    public String signDelete(){
        return "delete";
    }

    @PostMapping("/delete")
    public String signDeletePost(@RequestParam("email") String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        userRepository.delete(user);
        //String URL = "/users:/success";
        return "redirect:allUser";
    }

    //MODEL AND VIEW
    @ModelAttribute("users")
    public List<User> listAll(){
        return userRepository.findAll();
    }

}
