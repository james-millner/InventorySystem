package com.jm.InventorySystem.controller;

import com.mongodb.MongoClient;
import com.sun.xml.internal.ws.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.jm.InventorySystem.domain.Storehouse;
import com.jm.InventorySystem.DAO.MongoDBStorehouseDAO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by James on 02/03/2016.
 */

@Controller
public class HomeController extends HttpServlet {

    @RequestMapping("/homepage")
    public String homepage() {
        return "homepage";
    }

    @RequestMapping("/inventory")
    public String Inventory() {
        return "/main/inventory";
    }

    @RequestMapping("/assets")
    public String Assets() {
        return "/main/assets";
    }

    @RequestMapping("/users")
    public String Users(Model model,@CookieValue(value = "user") String user) {

        String type = user.substring(user.lastIndexOf("-") + 1);
        if(type.equals("Admin")) {
            System.out.println("WELCOME ADMIN");
        } else {
            System.out.println("NORM USER");
        }
        model.addAttribute("username", user);
        model.addAttribute("type", type);
        return "/main/users";
    }

}
