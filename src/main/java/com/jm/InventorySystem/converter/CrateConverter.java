package com.jm.InventorySystem.converter;

import com.jm.InventorySystem.domain.Crate;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by James on 31/03/2016.
 */
public class CrateConverter {

    //Converts a crate into a DB Object.
    public static DBObject toDBObject(Crate crate) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append(
                "sid", crate.getSid()).append(
                "cName", crate.getcName()).append(
                "width", crate.getWidth()).append(
                "height", crate.getHeight()).append(
                "dateCreated", crate.getDateCreated());
        if(crate.getId() != null) {
            builder = builder.append("_id", new ObjectId(crate.getId()));
        }
        return builder.get();
    }

    public static Crate toCrate(DBObject obj) {
        Crate c = new Crate();
        ObjectId id = (ObjectId) obj.get("_id");
        c.setId(id.toString());
        c.setSid((String) obj.get("sid"));
        c.setcName((String) obj.get("cName"));
        c.setWidth((Integer) obj.get("width"));
        c.setHeight((Integer) obj.get("height"));
        c.setDateCreated((Date) obj.get("dateCreated"));
        return  c;
    }

}
