package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Asset;
import com.jm.InventorySystem.domain.Inventory;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

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
            String JSONInventory = mapper.writeValueAsString(inventory);
            DBObject dbObjectInv = (DBObject) JSON.parse(JSONInventory);
            this.db.insert(dbObjectInv);
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

    public Inventory getInventory(Inventory i) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", i.get_id());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                i = mapper.readValue(objJSON, Inventory.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public void deleteInventory(Inventory i) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", i.get_id());
        this.db.remove(findSpecific);
    }
}
