package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Asset;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
public class MongoDBAssetDAO {

    public DBCollection db;

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

    public Integer countType(String type) {
        ObjectMapper mapper = new ObjectMapper();
        int total = 0;
        BasicDBObject findTypes = new BasicDBObject();
        findTypes.put("type", type);
        try {
            DBCursor cursor = db.find(findTypes);
            total = cursor.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    //Descending
    public List<Asset> sortByDate() {
        ObjectMapper mapper = new ObjectMapper();
        List<Asset> assets = new ArrayList<Asset>();
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("dateCreated", -1);
        try {
            DBCursor cursor = db.find().sort(dbObject);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                Asset i = mapper.readValue(objJSON, Asset.class);
                assets.add(i);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return assets;
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

    public List<Asset> getAssetsByCrate(Asset a) {
        List<Asset> results = new ArrayList<Asset>();
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("cid", a.getCid());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                a = mapper.readValue(objJSON, Asset.class);
                results.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<Asset> getAssetsByType(Asset a) {
        List<Asset> results = new ArrayList<Asset>();
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("type", a.getType());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                a = mapper.readValue(objJSON, Asset.class);
                results.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public void updateAsset(Asset asset) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("_id", asset.get_id());
            DBCursor cursor = this.db.find(whereQuery);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String update = mapper.writeValueAsString(asset);
                DBObject updateAsset = (DBObject) JSON.parse(update);
                this.db.update(whereQuery, updateAsset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteAsset(Asset i) {
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", i.get_id());
        this.db.remove(findSpecific);
    }

}
