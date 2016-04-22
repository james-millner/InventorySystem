package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.InventoryType;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 21/04/2016.
 */
public class MongoDBInvTypeDAO {

    private DBCollection db;

    public MongoDBInvTypeDAO(MongoClient mongoClient) { this.db = mongoClient.getDB("InventorySys").getCollection("invtypes"); }

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

    public InventoryType getType(InventoryType type) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", type.get_id());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                type = mapper.readValue(objJSON, InventoryType.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    public List<InventoryType> readAllTypes() {
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

    public void deleteType(InventoryType type) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("type", type.getType()).get();
        this.db.remove(query);
    }


}
