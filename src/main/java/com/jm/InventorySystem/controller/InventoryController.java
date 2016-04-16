package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.DAO.MongoDBInventoryDAO;
import com.jm.InventorySystem.domain.Crate;
import com.jm.InventorySystem.domain.Inventory;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
@Controller
public class InventoryController {

    @RequestMapping("/inventory")
    public String Inventory(Model model){
        return "/main/inventory";

    }

    @RequestMapping("/inventory/addInventory")
    public String addInv(Model model,
                         Inventory inventory) {
        // Since 2.10.0, uses MongoClient
        MongoClient mongoCrate = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongoCrate);
        List<Crate> crates = crateDAO.readAllCrate();
        model.addAttribute("crateList", crates);
        mongoCrate.close();

        MongoClient mongoInventory = new MongoClient("localhost", 27017);
        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongoInventory);
        List<Inventory> invList = inventoryDAO.readAllInventory();
        model.addAttribute("inventoryList", invList);

        if(inventory.getIname() == null) {
            return "/main/Inventory/addInventory";
        } else {
            Date date = new Date();
            inventory.setDateCreated(date);
            inventoryDAO.createInventory(inventory);
            mongoInventory.close();
            return "/main/Inventory/addInventory";
        }
    }

    @RequestMapping("/inventory/viewAll")
    public String viewAll(Model model,
                          Inventory inventory) {

        MongoClient mongoInventory = new MongoClient("localhost", 27017);
        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongoInventory);
        List<Inventory> invList = inventoryDAO.readAllInventory();
        model.addAttribute("inventoryList", invList);
        mongoInventory.close();

        return "/main/Inventory/viewall";

    }
}
