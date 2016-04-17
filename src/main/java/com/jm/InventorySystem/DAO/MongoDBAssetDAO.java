package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Asset;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
public class MongoDBAssetDAO {

    private DBCollection db;

    public MongoDBAssetDAO(MongoClient mongo) {
        this.db = mongo.getDB("InventorySys").getCollection("assets");
    }

    //Adds a storehouse to StorehouseCollection.
    public void createAsset(Asset s) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectId id = new ObjectId();
        s.set_id(id.toString());
        try {
            String JSONAsset = mapper.writeValueAsString(s);
            DBObject dbObject = (DBObject) JSON.parse(JSONAsset);
            this.db.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Asset> readAllAssets() {
        ObjectMapper mapper = new ObjectMapper();
        List<Asset> assets = new ArrayList<Asset>();
        try {
            DBCursor cursor = db.find();
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                Asset a = mapper.readValue(objJSON, Asset.class);
                assets.add(a);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return assets;
    }

    public Asset getAsset(Asset a) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", a.get_id());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                a = mapper.readValue(objJSON, Asset.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public void deleteAsset(Asset i) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", i.get_id());
        this.db.remove(findSpecific);
    }

}
