package com.jm.InventorySystem.DAO;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.jm.InventorySystem.domain.Storehouse;
import com.jm.InventorySystem.converter.StorehouseConverter;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


/**
 * Created by James on 05/03/2016.
 */
public class MongoDBStorehouseDAO {

    private DBCollection db;

    public MongoDBStorehouseDAO(MongoClient mongo) {
        this.db = mongo.getDB("InventorySys").getCollection("storehouses");
    }

    //Adds a storehouse to StorehouseCollection.
    public Storehouse createStorehouse(Storehouse s) {
        DBObject doc = StorehouseConverter.toDBObject(s);
        this.db.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        s.set_id(id.toString());
        return s;
    }

    public List<Storehouse> readAllStorehouses() {
        List<Storehouse> houses = new ArrayList<Storehouse>();
        DBCursor cursor = db.find();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            Storehouse h = StorehouseConverter.toStorehouse(obj);
            houses.add(h);
        }
        return houses;
    }

    public Storehouse readStorehouse(Storehouse s) {
        DBObject query = BasicDBObjectBuilder.start().append(
                "_id", new ObjectId(s.get_id())).get();
        DBObject data = this.db.findOne(query);
        return StorehouseConverter.toStorehouse(data);
    }

    public void updateStorehouse(Storehouse h) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(h.get_id())).get();
        this.db.update(query, StorehouseConverter.toDBObject(h));
    }

    public void deleteStorehouse(Storehouse h) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(h.get_id())).get();
        this.db.remove(query);
    }


}
