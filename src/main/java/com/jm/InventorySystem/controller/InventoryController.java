package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.DAO.MongoDBInventoryDAO;
import com.jm.InventorySystem.DAO.MongoDBStorehouseDAO;
import com.jm.InventorySystem.DAO.MongoDBTypeDAO;
import com.jm.InventorySystem.domain.Crate;
import com.jm.InventorySystem.domain.Inventory;
import com.jm.InventorySystem.domain.InventoryType;
import com.jm.InventorySystem.domain.Storehouse;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        MongoClient mongoInventoryType = new MongoClient("localhost", 27017);
        MongoDBTypeDAO invType = new MongoDBTypeDAO(mongoInventoryType);
        List<InventoryType> types = invType.readAllTypes();
        model.addAttribute("types", types);
        mongoInventoryType.close();

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

    @RequestMapping("/inventory/settings")
    public String invSettings(Model model,
                              String type) {

        MongoClient mongoInventoryType = new MongoClient("localhost", 27017);
        MongoDBTypeDAO invType = new MongoDBTypeDAO(mongoInventoryType);
        List<InventoryType> types = invType.readAllTypes();
        model.addAttribute("types", types);
        mongoInventoryType.close();
        if(type == null) {
            return "/main/Inventory/settings";
        }
        MongoClient mInv = new MongoClient("localhost", 27017);
        MongoDBTypeDAO mongoDBTypeDAO = new MongoDBTypeDAO(mInv);
        InventoryType typeNew = new InventoryType(type);
        mongoDBTypeDAO.createType(typeNew);

        return "redirect:/inventory/settings";
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

    @RequestMapping("/inventory/viewInventory")
    public String viewInv(Model model,
                          Inventory inventory,
                          @RequestParam("_id") String id) {
        MongoClient mongoInventory = new MongoClient("localhost", 27017);
        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongoInventory);
        inventory.set_id(id);
        Inventory inv = inventoryDAO.getInventory(inventory);
        model.addAttribute("item", inv);
        //Get Date Difference
        Date createdDate = inv.getDateCreated();
        Date today = new Date();
        long difference = today.getDay() - createdDate.getDay();
        model.addAttribute("daysInSys", difference);
        mongoInventory.close();

        String cid = inv.getCid();
        if (cid == null) {
            model.addAttribute("na", "N/A");
            return "/main/Inventory/viewInventory";
        } else {
            //Get Crate Info.
            MongoClient mongoCrate = new MongoClient("localhost", 27017);
            MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongoCrate);
            Crate c = new Crate();
            c.set_id(cid);
            c = crateDAO.getCrate(c);
            model.addAttribute("crate", c);
            mongoCrate.close();

            //Get Storehouse Info.
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongoClient);
            Storehouse h = new Storehouse();
            h.set_id(c.getSid());
            h = storehouseDAO.readStorehouse(h);
            model.addAttribute("storehouse", h);

            return "/main/Inventory/viewInventory";
        }
    }

    @RequestMapping("/inventory/viewInventory/update")
    public String updateAsset(Model model,
                              Inventory inventory,
                              @RequestParam("_id") String id) {
        MongoClient mongoInventory = new MongoClient("localhost", 27017);
        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongoInventory);

        Inventory blank = new Inventory();
        blank.set_id(id);
        blank = inventoryDAO.getInventory(blank);

        Date date = blank.getDateCreated();
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongoClient);

        MongoClient mongoInventoryType = new MongoClient("localhost", 27017);
        MongoDBTypeDAO invType = new MongoDBTypeDAO(mongoInventoryType);
        List<InventoryType> types = invType.readAllTypes();
        model.addAttribute("types", types);
        if(inventory.getIname() == null) {

            inventory.set_id(id);
            inventory = inventoryDAO.getInventory(inventory);
            model.addAttribute("inventory", inventory);
            mongoInventory.close();

            List<Crate> crates = crateDAO.readAllCrate();
            model.addAttribute("crateList", crates);
            mongoClient.close();
            return "/main/Inventory/viewInventoryUpdate";
        } else {
            inventory.set_id(id);
            inventory.setDateCreated(date);
            if(inventory.getCid().equals("")){
                inventory.setCid(null);
            }
            inventoryDAO.updateInventory(inventory);
            mongoInventory.close();
            return "redirect:/inventory/viewInventory?_id=" + id;
        }

    }

    @RequestMapping("/inventory/del")
    public String delInv(Model model,
                          Inventory inventory,
                          @RequestParam("_id") String id) {
        MongoClient mongoInventory = new MongoClient("localhost", 27017);
        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongoInventory);
        inventory.set_id(id);
        inventoryDAO.deleteInventory(inventory);
        return "redirect:/inventory/viewAll";
    }
}
