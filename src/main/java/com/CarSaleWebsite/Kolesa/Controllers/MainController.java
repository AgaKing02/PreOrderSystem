package com.CarSaleWebsite.Kolesa.Controllers;


import com.CarSaleWebsite.Kolesa.Models.Food;
import com.CarSaleWebsite.Kolesa.Models.Usr;
import com.CarSaleWebsite.Kolesa.Repositories.FoodRepository;
import com.CarSaleWebsite.Kolesa.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
public class MainController {
    @Autowired
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final FoodRepository foodRepository;

    public MainController(UsersRepository usersRepository, PasswordEncoder passwordEncoder, FoodRepository foodRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.foodRepository = foodRepository;
    }

    @GetMapping("/")
    public String MainPage(Model model) {
        Iterable<Food> foods=foodRepository.findAll();
        model.addAttribute("foods",foods);
        return "main-page";
    }

    @GetMapping("/login")
    public String LoginPage() {

        return "login-page";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Iterable<Usr> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "users-page";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        Usr auth=usersRepository.findByUsername(principal.getName());
        String role=auth.getRoles();
        model.addAttribute("authuser", principal.getName());
        model.addAttribute("role",role);
        return "profile-page";
    }

    @GetMapping("/users/add")
    public String addUserPage() {
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUserAction(@RequestParam String txtUsername,
                                @RequestParam String txtPassword,
                                @RequestParam String role,
                                @RequestParam String permission) {
        Usr user= new Usr(txtUsername,passwordEncoder.encode(txtPassword),role,permission);
        usersRepository.save(user);

        return "redirect:/users";
    }
    @GetMapping("/about")
    public String aboutPage(){
        return "about-page";
    }



}


