package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBAssetDAO;
import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.DAO.MongoDBInventoryDAO;
import com.jm.InventorySystem.DAO.MongoDBStorehouseDAO;
import com.jm.InventorySystem.domain.Asset;
import com.jm.InventorySystem.domain.Crate;
import com.jm.InventorySystem.domain.Inventory;
import com.jm.InventorySystem.domain.Storehouse;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        MongoDBCrateDAO cDAO = new MongoDBCrateDAO(mongo);
        List<Crate> crates = cDAO.readAllCrate();
        model.addAttribute("cList", crates);

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

            return "redirect:/crates";
        }
    }

    @RequestMapping("/crates/viewCrate")
    public String viewCrate(Model model,
                            Crate crate,
                            @RequestParam("_id") String id){
        //Find crate
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongo);
        Crate blank = new Crate();
        blank.set_id(id);
        crate = crateDAO.getCrate(blank);
        //Find Storehouse
        String sid = crate.getSid();
        MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongo);
        Storehouse h = new Storehouse();
        h.set_id(sid);
        Storehouse found = storehouseDAO.readStorehouse(h);

        model.addAttribute("crate", crate);
        model.addAttribute("storehouse", found);

        return "/main/Crate/viewCrate";
    }

    @RequestMapping("/crates/delCrate")
    public String delCrate(@RequestParam("_id") String id, Model model) {
        //Find crate
        //Find crate
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongo);
        Crate blank = new Crate();
        blank.set_id(id);
        Crate crate = crateDAO.getCrate(blank);
        //Find Storehouse
        String sid = crate.getSid();
        MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongo);
        Storehouse h = new Storehouse();
        h.set_id(sid);
        Storehouse found = storehouseDAO.readStorehouse(h);

        model.addAttribute("crate", crate);
        model.addAttribute("storehouse", found);

        //Check for Assets
        Asset blankAsset = new Asset();
        blankAsset.setCid(id);

        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongo);
        List<Asset> a = assetDAO.getAssetsByCrate(blankAsset);

        //Check for Inventory.
        Inventory blankInv = new Inventory();
        blankInv.setCid(id);

        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongo);
        List<Inventory> i = inventoryDAO.getInventoryByCrate(blankInv);

        if(a.size() > 0 | i.size() > 0) {
            model.addAttribute("bool", "true");
            return "/main/Crate/viewCrate";
        } else {
            crateDAO.deleteCrate(blank);
        }
        return "redirect:/crates";


    }
}
