package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.DAO.MongoDBStorehouseDAO;
import com.jm.InventorySystem.domain.Crate;
import com.jm.InventorySystem.domain.Storehouse;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by James on 31/03/2016.
 */
@Controller
public class CrateController {

    @RequestMapping("/crates")
    public String Crates(Model model,
                         Crate crate) {

        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBStorehouseDAO shDAO = new MongoDBStorehouseDAO(mongo);

        //Read all storehouses and add them to the crate model.
        List<Storehouse> storehouses = shDAO.readAllStorehouses();
        model.addAttribute("shList", storehouses);

        if(crate.getcName() == null) {
            mongo.close();
            return "/main/crates";
        } else {

            Date date = new Date();

            crate.setDateCreated(date);
            System.out.println("***" + crate.getSid());
            MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongo);
            crateDAO.createCrate(crate);
            mongo.close();

            return "/main/crates";
        }
    }
}
