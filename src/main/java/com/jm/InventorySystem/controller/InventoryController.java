package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.domain.Crate;
import com.jm.InventorySystem.domain.Inventory;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
@Controller
public class InventoryController {

    @RequestMapping("/inventory")
    public String Inventory(Model model,
                            Inventory inventory) {
        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongo);
        List<Crate> crates = crateDAO.readAllCrate();
        model.addAttribute("crateList", crates);

        if(inventory.getIname() == null) {
            return "/main/inventory";
        } else {
            System.out.println(inventory.getDescription());
            return "/main/inventory";
        }

    }
}
