package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.InventoryType;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 21/04/2016.
 */
public class MongoDBTypeDAO {

    private DBCollection db;

    public MongoDBTypeDAO(MongoClient mongoClient) { this.db = mongoClient.getDB("InventorySys").getCollection("invtypes"); }

    public void createType(InventoryType type) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectId id = new ObjectId();
        type.set_id(id.toString());
        try {
            String JSONCrate = mapper.writeValueAsString(type);
            DBObject dbObject = (DBObject) JSON.parse(JSONCrate);
            this.db.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<InventoryType> readAllCrate() {
        ObjectMapper mapper = new ObjectMapper();
        List<InventoryType> types = new ArrayList<InventoryType>();
        try {
            DBCursor cursor = db.find();
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                InventoryType type = mapper.readValue(objJSON, InventoryType.class);
                types.add(type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }

}
