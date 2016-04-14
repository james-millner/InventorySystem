package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Asset;
import com.jm.InventorySystem.domain.Inventory;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
public class MongoDBInventoryDAO {

    private DBCollection db;

    public MongoDBInventoryDAO(MongoClient mongo) {
        this.db = mongo.getDB("InventorySys").getCollection("inventory");
    }

    //Adds a storehouse to StorehouseCollection.
    public void createInventory(Inventory inventory) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectId id = new ObjectId();
        inventory.set_id(id.toString());
        try {
            String JSONAsset = mapper.writeValueAsString(inventory);
            DBObject dbObject = (DBObject) JSON.parse(JSONAsset);
            this.db.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Inventory> readAllInventory() {
        ObjectMapper mapper = new ObjectMapper();
        List<Inventory> inventory = new ArrayList<Inventory>();
        try {
            DBCursor cursor = db.find();
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                Inventory i = mapper.readValue(objJSON, Inventory.class);
                inventory.add(i);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return inventory;
    }
}
