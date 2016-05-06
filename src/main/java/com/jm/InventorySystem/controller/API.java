package com.jm.InventorySystem.controller;

import com.jm.InventorySystem.DAO.*;
import com.jm.InventorySystem.converter.UserConverter;
import com.jm.InventorySystem.domain.User;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 01/04/2016.
 */

@RestController
public class API {

    MongoClient mongo = new MongoClient("localhost", 27017);

    @RequestMapping(value = "/api/users")
    public DBObject user(@RequestParam("name") String name) {
        // Since 2.10.0, uses MongoClient

        MongoDBUserDAO userDAO = new MongoDBUserDAO(mongo);
        User user = new User();
        user.setUsername(name);
        User got = userDAO.getUserByName(user);
        return UserConverter.toDBObject(got);
    }

    @RequestMapping(value = "/api/inventory")
    public List<DBObject> allInventory() {
        MongoDBInventoryDAO inventoryDAO = new MongoDBInventoryDAO(mongo);
        DBCursor cursor = inventoryDAO.db.find();
        List<DBObject> invs = new ArrayList<DBObject>();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            invs.add(obj);
        }
        return invs;
    }

    @RequestMapping(value = "/api/assets")
    public List<DBObject> allAssets() {
        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongo);
        DBCursor cursor = assetDAO.db.find();
        List<DBObject> assets = new ArrayList<DBObject>();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            assets.add(obj);
        }
        return assets;
    }

    @RequestMapping(value = "/api/storehouses")
    public List<DBObject> allstorehouses() {
        MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongo);
        DBCursor cursor = storehouseDAO.db.find();
        List<DBObject> storehouses = new ArrayList<DBObject>();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            storehouses.add(obj);
        }
        return storehouses;
    }

    @RequestMapping(value = "/api/crates")
    public List<DBObject> allcrates() {
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongo);
        DBCursor cursor = crateDAO.db.find();
        List<DBObject> crates = new ArrayList<DBObject>();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            crates.add(obj);
        }
        return crates;
    }
}
