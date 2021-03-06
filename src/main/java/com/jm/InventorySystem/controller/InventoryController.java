package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.*;
import com.jm.InventorySystem.domain.*;
import com.mongodb.MongoClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
@Controller
public class InventoryController {

    @RequestMapping("/inventory")
    public String Inventory(Model model){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Set up access to generate inventory stats.
        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongoClient);
        MongoDBInvTypeDAO invTypeDAO = new MongoDBInvTypeDAO(mongoClient);
        //Clear statistics.
        MongoDBStatsDAO statsDAO = new MongoDBStatsDAO(mongoClient);
        statsDAO.dbInvStats.drop();
        //Get all the types in the system.
        List<InventoryType> allTypes = invTypeDAO.readAllTypes();
        List<Statistics> stats = new ArrayList<Statistics>();

        for(int i = 0; i < allTypes.size(); i++) {
            String type = allTypes.get(i).getType();
            long value = inventoryDAO.countType(type);
            Statistics stat = new Statistics();
            stat.setName(type);
            stat.setValue(value);
            stats.add(stat);
            statsDAO.createInvStat(stat);
        }
        model.addAttribute("stats", stats);

        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongoClient);
        //List of all inventory by date.
        List<Inventory> byDate = inventoryDAO.sortByDate();
        //List of all crates.
        List<Crate> crates = crateDAO.readAllCrate();
        model.addAttribute("invByDate", byDate);
        List<Inventory> inventWithCrateName = new ArrayList<Inventory>();
        for(int i = 0; i < byDate.size(); i++) {
            Inventory at = byDate.get(i);
            for(int a = 0; a < crates.size(); a++) {
                Crate cat = crates.get(a);
                if(at.getCid().equals(cat.get_id())){
                    at.setCid(cat.getcName());
                    at.setExtraInfo(cat.get_id());
                }
            }
            inventWithCrateName.add(at);
        }

        model.addAttribute("invWithCrate", inventWithCrateName);

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
        MongoDBInvTypeDAO invType = new MongoDBInvTypeDAO(mongoInventoryType);
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
        MongoDBInvTypeDAO invType = new MongoDBInvTypeDAO(mongoInventoryType);
        List<InventoryType> types = invType.readAllTypes();
        model.addAttribute("types", types);
        mongoInventoryType.close();
        if(type == null) {
            return "/main/Inventory/settings";
        }
        MongoClient mInv = new MongoClient("localhost", 27017);
        MongoDBInvTypeDAO mongoDBInvTypeDAO = new MongoDBInvTypeDAO(mInv);
        InventoryType typeNew = new InventoryType(type);
        mongoDBInvTypeDAO.createType(typeNew);

        return "redirect:/inventory/settings";
    }

    @RequestMapping("/inventory/deleteType")
    public String delType(@RequestParam("type") String type, Model model) {
        MongoClient mInv = new MongoClient("localhost", 27017);
        //Get all types.
        MongoDBInvTypeDAO mongoDBInvTypeDAO = new MongoDBInvTypeDAO(mInv);
        List<InventoryType> types = mongoDBInvTypeDAO.readAllTypes();
        model.addAttribute("types", types);

        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mInv);
        Inventory i = new Inventory();
        i.setType(type);
        List<Inventory> inventories = inventoryDAO.getInventoryByType(i);
        if(inventories.size() > 0) {
            model.addAttribute("bool", "true");
            return "/main/Inventory/settings";
        } else {
            InventoryType iType = new InventoryType();
            iType.setType(type);
            iType = mongoDBInvTypeDAO.getType(iType);
            mongoDBInvTypeDAO.deleteType(iType);

            return "redirect:/inventory/settings";
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
        MongoDBInvTypeDAO invType = new MongoDBInvTypeDAO(mongoInventoryType);
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
