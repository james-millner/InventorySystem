package com.jm.InventorySystem.controller;

import org.springframework.stereotype.Controller;
import com.mongodb.MongoClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.jm.InventorySystem.domain.Storehouse;
import com.jm.InventorySystem.DAO.MongoDBStorehouseDAO;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by James on 05/03/2016.
 */
@Controller
public class EditStorehouseController {

    @RequestMapping("/editStorehouse")
    public String editStorehouse(Model model,
                                 Storehouse house,
                                 @RequestParam("_id") String id) {

        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongo);

        if(house.getName() == null){
            //Use the passed _id param to find the item to edit.
            Storehouse find = new Storehouse();
            find.setId(id);
            //Use the Data Access Object to find the result.
            house = storehouseDAO.readStorehouse(find);
            //Add the object to the model. Displaying it for the user.
            model.addAttribute("houseM", house);
        } else {
            //Once it finds the house, gett all the updated fields.
            house.setId(id);
            house.setName(house.getName());
            house.setSize(house.getSize());
            house.setAccess(house.getAccess());
            house.setAddress(house.getAddress());
            house.setOwned(house.isOwned());
            house.setRented(house.isRented());
            house.setActive(house.isActive());

            //Use Data Access Object to update the storehouse.
            storehouseDAO.updateStorehouse(house);

            return "redirect:/storehouses";
        }
        return  "editStorehouse";
    }
    @RequestMapping("/deleteStorehouse")
    public String deleteStorehouse(Model model,
                                   Storehouse house,
                                   @RequestParam("_id") String id) {

        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongo);

        //Use the passed _id param to find the item to edit.
        Storehouse find = new Storehouse();
        find.setId(id);
        //Use the Data Access Object to find the result.
        storehouseDAO.deleteStorehouse(find);

        return "redirect:/storehouses";

    }

}
