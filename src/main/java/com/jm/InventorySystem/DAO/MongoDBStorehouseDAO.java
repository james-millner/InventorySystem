package com.jm.InventorySystem.DAO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import com.jm.InventorySystem.domain.Storehouse;


/**
 * Created by James on 05/03/2016.
 */
public class MongoDBStorehouseDAO {

    public DBCollection db;

    public MongoDBStorehouseDAO(MongoClient mongo) {
        this.db = mongo.getDB("InventorySys").getCollection("storehouses");
    }

    //Adds a storehouse to StorehouseCollection.
    public void createStorehouse(Storehouse storehouse) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectId id = new ObjectId();
        storehouse.set_id(id.toString());
        try {
            String JSONStorehouse = mapper.writeValueAsString(storehouse);
            DBObject dbObject = (DBObject) JSON.parse(JSONStorehouse);
            this.db.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Storehouse> readAllStorehouses() {
        ObjectMapper mapper = new ObjectMapper();
        List<Storehouse> storehouses = new ArrayList<Storehouse>();
        try {
            DBCursor cursor = db.find();
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                Storehouse storehouse = mapper.readValue(objJSON, Storehouse.class);
                storehouses.add(storehouse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return storehouses;
    }

    public Storehouse readStorehouse(Storehouse s) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", s.get_id());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                s = mapper.readValue(objJSON, Storehouse.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void updateStorehouse(Storehouse s) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("_id", s.get_id());
            DBCursor cursor = this.db.find(whereQuery);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String update = mapper.writeValueAsString(s);
                DBObject updateHouse = (DBObject) JSON.parse(update);
                this.db.update(whereQuery, updateHouse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void deleteStorehouse(Storehouse h) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(h.get_id())).get();
        this.db.remove(query);
    }


}
