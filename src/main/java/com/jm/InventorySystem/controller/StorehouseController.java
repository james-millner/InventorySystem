package com.jm.InventorySystem.controller;


import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
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
 * Created by James on 05/03/2016.
 */
@Controller
public class StorehouseController {

    @RequestMapping("/storehouses")
    public String Storehouses(Model model,
                              Storehouse sh) throws Exception {

        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongo);

        List<Storehouse> lists = storehouseDAO.readAllStorehouses();
        System.out.println(lists.size());
        model.addAttribute("storehouseList", lists);

        if (sh.getName() == null) {
            return "/main/storehouses";
        } else {


            System.out.println("SHName: " + sh.getName() + "\n" +
                    "shSize:" + sh.getSize() + "\n" +
                    "access" + sh.getAccess() + "\n" +
                    "Address: " + sh.getAddress() + "\n" +
                    "active: " + sh.isOwned() + "\n" +
                    "rented: " + sh.isRented() + "\n" +
                    "Active: " + sh.isActive());


            Storehouse storehouse = new Storehouse(sh.getName(), sh.getSize(), sh.getAccess(), sh.getAddress(), sh.isOwned(), sh.isRented(), sh.isActive());
            storehouseDAO.createStorehouse(storehouse);

            return "redirect:/storehouses";
        }
    }


}
