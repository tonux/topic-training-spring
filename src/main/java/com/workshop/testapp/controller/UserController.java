package com.workshop.testapp.controller;

import com.workshop.testapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {

    @Autowired

    //Afficher la lister des utilisateurs
    @GetMapping("/list")
    public String list() {
        return "user-list";
    }

    //Afficher le formulaire d'ajout d'un utilisateur
    @GetMapping("/form")
    public String form() {
        return "user-form";
    }

    //Afficher le formulaire de modification d'un utilisateur
    @GetMapping("/edit")
    public String edit() {
        return "user-edit";
    }


    //Ajouter un utilisateur
    @GetMapping("/add")
    public String add() {
        return "user-add";
    }

    //Modifier un utilisateur
    @GetMapping("/update")
    public String update() {
        return "user-update";
    }

    //Supprimer un utilisateur
    @GetMapping("/delete")
    public String delete() {
        return "user-delete";
    }


}
