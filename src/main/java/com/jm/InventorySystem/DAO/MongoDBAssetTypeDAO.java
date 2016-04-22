package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.AssetType;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 21/04/2016.
 */
public class MongoDBAssetTypeDAO {

    private DBCollection db;

    public MongoDBAssetTypeDAO(MongoClient mongoClient) { this.db = mongoClient.getDB("InventorySys").getCollection("asttypes"); }

    public void createType(AssetType type) {
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

    public AssetType getType(AssetType type) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", type.get_id());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                type = mapper.readValue(objJSON, AssetType.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    public List<AssetType> readAllTypes() {
        ObjectMapper mapper = new ObjectMapper();
        List<AssetType> types = new ArrayList<AssetType>();
        try {
            DBCursor cursor = db.find();
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                AssetType type = mapper.readValue(objJSON, AssetType.class);
                types.add(type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }

    public void deleteType(AssetType type) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("type", type.getType()).get();
        this.db.remove(query);
    }


}
