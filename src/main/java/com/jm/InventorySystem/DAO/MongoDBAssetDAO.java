package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Asset;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

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
        try {
            String JSONAsset = mapper.writeValueAsString(s);
            DBObject dbObject = (DBObject) JSON.parse(JSONAsset);
            this.db.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
