package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBUserDAO;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jm.InventorySystem.domain.User;

import java.util.List;

/**
 * Created by James on 03/04/2016.
 */
@Controller
public class UserController {

    @RequestMapping("/users")
    public String Users(Model model, @CookieValue(value = "user") String user) {

        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);

        List<User> userList = userDAO.readAllUsers();
        String type = user.substring(user.lastIndexOf("-") + 1);
        if(type.equals("Admin")) {
            System.out.println("WELCOME ADMIN");
            model.addAttribute("users", userList);
        } else {
            System.out.println("NORM USER");
        }
        model.addAttribute("username", user);
        model.addAttribute("type", type);
        return "/main/users";
    }

}
