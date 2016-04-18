package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Crate;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 03/04/2016.
 */
public class MongoDBCrateDAO {

    private DBCollection db;

    public MongoDBCrateDAO(MongoClient mongo) {
        this.db = mongo.getDB("InventorySys").getCollection("crates");
    }

    //Adds a storehouse to StorehouseCollection.
    public void createCrate(Crate crate) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectId id = new ObjectId();
        crate.set_id(id.toString());
        try {
            String JSONCrate = mapper.writeValueAsString(crate);
            DBObject dbObject = (DBObject) JSON.parse(JSONCrate);
            this.db.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Crate getCrate(Crate crate) {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject findSpecific = new BasicDBObject();
        findSpecific.put("_id", crate.get_id());
        try {
            DBCursor cursor = db.find(findSpecific);
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                crate = mapper.readValue(objJSON, Crate.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return crate;
    }

    public List<Crate> readAllCrate() {
        ObjectMapper mapper = new ObjectMapper();
        List<Crate> crates = new ArrayList<Crate>();
        try {
            DBCursor cursor = db.find();
            while (cursor.hasNext()) {
                DBObject obj = cursor.next();
                String objJSON = obj.toString();
                Crate crate = mapper.readValue(objJSON, Crate.class);
                crates.add(crate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return crates;
    }
}
