package com.jm.InventorySystem.converter;

import org.bson.types.ObjectId;

import com.jm.InventorySystem.domain.Storehouse;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
/**
 * Created by James on 05/03/2016.
 */
public class StorehouseConverter {

    // convert a Storehouse Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Storehouse s) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append(
                "name", s.getName()).append(
                "size", s.getSize()).append(
                "access", s.getAccess()).append(
                "address", s.getAddress()).append(
                "isOwned", s.isOwned()).append(
                "isRented", s.isRented()).append(
                "isActive", s.isActive());
        if(s.get_id() != null) {
            builder = builder.append("_id", new ObjectId(s.get_id()));
        }
        return builder.get();
    }
    public static Storehouse toStorehouse(DBObject obj) {
        Storehouse s = new Storehouse();
        ObjectId id = (ObjectId) obj.get("_id");
        s.set_id(id.toString());
        s.setName(obj.get("name").toString());
        s.setSize((Integer) obj.get("size"));
        s.setAccess(obj.get("access").toString());
        s.setAddress(obj.get("address").toString());
        s.setOwned((Boolean) obj.get("isOwned"));
        s.setRented((Boolean) obj.get("isRented"));
        s.setActive((Boolean) obj.get("isActive"));
        return s;

    }

}
