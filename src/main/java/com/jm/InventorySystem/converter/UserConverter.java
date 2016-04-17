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
        if(u.get_id() != null) {
            builder = builder.append("_id", new ObjectId(u.get_id()));
        }
        return builder.get();
    }

    //Convert a DB object into a Java Object.
    public static User toUser(DBObject obj) {
        User u = new User();
        ObjectId id = (ObjectId) obj.get("_id");
        u.set_id(id.toString());
        u.setfName((String) obj.get("fName"));
        u.setsName((String) obj.get("sName"));
        u.setType((String) obj.get("type"));
        u.setUsername((String) obj.get("username"));
        u.setPassword((String) obj.get("password"));
        u.setDateCreated((Date) obj.get("dateCreated"));
        return u;
    }
}
