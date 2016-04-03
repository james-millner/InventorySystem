package com.jm.InventorySystem.DAO;

import com.jm.InventorySystem.converter.CrateConverter;
import com.jm.InventorySystem.converter.StorehouseConverter;
import com.jm.InventorySystem.domain.Crate;
import com.jm.InventorySystem.domain.Storehouse;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
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
    public Crate createCrate(Crate crate) {
        DBObject doc = CrateConverter.toDBObject(crate);
        this.db.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        crate.setId(id.toString());
        return crate;
    }

    public List<Crate> readAllCrate() {
        List<Crate> crates = new ArrayList<Crate>();
        DBCursor cursor = db.find();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            Crate c = CrateConverter.toCrate(obj);
            crates.add(c);
        }
        return crates;
    }

}
