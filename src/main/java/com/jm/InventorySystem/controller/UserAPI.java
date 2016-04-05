package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.MongoDBUserDAO;
import com.jm.InventorySystem.converter.UserConverter;
import com.jm.InventorySystem.domain.User;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by James on 01/04/2016.
 */

@RestController
public class UserAPI {

    @RequestMapping(value = "/api")
    public DBObject user(@RequestParam("name") String name) {
        // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        User user = new User();
        user.setUsername(name);
        User got = userDAO.getUserByName(user);
        return UserConverter.toDBObject(got);
    }
}
