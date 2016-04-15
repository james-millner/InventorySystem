package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Asset;
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



}
