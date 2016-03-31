package com.jm.InventorySystem.converter;

import com.jm.InventorySystem.domain.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by James on 31/03/2016.
 */
public class UserConverter {

    //Convert a user object to MongoDB Object.
    public static DBObject toDBObject(User u) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append("fName", u.getfName()).append("sName", u.getsName())
                .append("type", u.getType())
                .append("username", u.getUsername())
                .append("password", u.getPassword())
                .append("dateCreated", u.getDateCreated());

        //If the object already has an ID, search via that ID as well.
        if(u.getId() != null) {
            builder = builder.append("_id", new ObjectId(u.getId()));
        }
        return builder.get();
    }

    //Convert a DB object into a Java Object.
    public static User toUser(DBObject obj) {
        User u = new User();
        ObjectId id = (ObjectId) obj.get("_id");
        u.setFName((String) obj.get("fName"));
        u.setSName((String) obj.get("sName"));
        u.setType((String) obj.get("type"));
        u.setUsername((String) obj.get("username"));
        u.setPassword((String) obj.get("password"));
        u.setDateCreated((Date) obj.get("dateCreated"));
        return u;
    }
}
