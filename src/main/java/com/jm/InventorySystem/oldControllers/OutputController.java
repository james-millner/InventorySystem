package com.jm.InventorySystem.oldControllers;

import com.jm.InventorySystem.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

@Controller
public class OutputController {
    @RequestMapping(value = "/Test", method = RequestMethod.POST)
    public String authorInfo(Model model) {
 
       // Do something here
    	System.out.println("TEST METHOD");
        return "redirect:/hello";
    }
}