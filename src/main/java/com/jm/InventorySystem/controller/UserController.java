package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBUserDAO;
import com.jm.InventorySystem.domain.Encrypter;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jm.InventorySystem.domain.User;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/editUser")
    public String editUser(Model model,
                           @RequestParam("_id") String id,
                           User user,
                           @CookieValue(value = "user") String cookie,
                           String updateType,
                           String updatePass) {
        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);

        User userID = new User();
        userID.setId(id);
        user = userDAO.getUserByID(userID);
        model.addAttribute("user", user);

        String name = cookie.substring(0, cookie.lastIndexOf("-"));
        model.addAttribute("userLogged", name);

        if(updateType == null){
            System.out.println("YOU JUST GOT HERE");
        } else {
            //Update user.
            user.setType(updateType);
            userDAO.updateUser(user);
            return "redirect:/editUser?_id=" + id;
        }

        if (updatePass == null) {
            System.out.println("PASS NOT CHANGED YET");
        } else {
            Encrypter ec = new Encrypter(updatePass);
            user.setPassword(ec.encrypt());
            userDAO.updateUser(user);
            return "redirect:/editUser?_id=" + id;
        }

        return "editUser";
    }

    @RequestMapping("/delUser")
    public String deleteUser(@RequestParam("_id") String id,
                             User user,
                             @CookieValue(value = "user") String username) {
        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);

        User userID = new User();
        userID.setId(id);

        user = userDAO.getUserByID(userID);
        userDAO.deleteUser(user);
        return "redirect:/users";

    }

}
