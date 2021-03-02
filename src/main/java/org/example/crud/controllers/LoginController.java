package org.example.crud.controllers;

import org.example.crud.models.User;
import org.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/createUser")
    public  String registration(@ModelAttribute("user") User user) {
        user.setRoles(new HashSet<>());
        if (user.getName().equals("admin")) {
            user.getRoles().add(userService.findRoleByName("ROLE_ADMIN"));
        } else {
            user.getRoles().add(userService.findRoleByName("ROLE_USER"));
        }
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String newUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }
}
