package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.domain.Asset;
import com.jm.InventorySystem.domain.Crate;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
@Controller
public class AssetController {

    @RequestMapping("/assets")
    public String Assets(Model model,
                         Asset asset) {
        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongo);
        List<Crate> crates = crateDAO.readAllCrate();
        model.addAttribute("crateList", crates);

        if(asset.getAname() == null) {
            return "/main/assets";
        } else {
            System.out.println(asset.getAname());
            return "/main/assets";
        }

    }
}
