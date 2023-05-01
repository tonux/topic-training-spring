package com.workshop.testapp.controller;

import com.workshop.testapp.model.User;
import com.workshop.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //test
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    //Afficher la lister des utilisateurs
    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    //Afficher le formulaire d'ajout d'un utilisateur
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "/add");
        return "user-form";
    }

    //Afficher le formulaire de modification d'un utilisateur
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "/update/" + id);
        return "user-form";
    }


    //Ajouter un utilisateur
    @PostMapping("/add")
    public String add(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/list";
    }

    //Modifier un utilisateur
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User userForm) {
        User user = userService.getUserById(id);
        // copy properties from userForm to user
        user.copy(userForm);
        userService.updateUser(user);
        return "redirect:/list";
    }

    //Supprimer un utilisateur
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }

}
