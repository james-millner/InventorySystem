package com.jm.InventorySystem.DAO;

import com.jm.InventorySystem.domain.User;
import com.jm.InventorySystem.converter.UserConverter;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 31/03/2016.
 */
public class MongoDBUserDAO {

    private DBCollection db;

    public MongoDBUserDAO(MongoClient mongo) {
        this.db = mongo.getDB("InventorySys").getCollection("users");
    }

    public DBCollection returnDB() {
        return this.db;
    }

    public User createUser(User user) {
        DBObject obj = UserConverter.toDBObject(user);
        this.db.insert(obj);
        ObjectId id = (ObjectId) obj.get("_id");
        user.set_id(id.toString());
        return user;
    }

    public User getUserByName(User user) {
        String name = user.getUsername();
        DBObject query = new BasicDBObject();
        query.put("username", name);
        DBCursor cursor = db.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            user = UserConverter.toUser(obj);
        }
        return user;
    }

    public User getUserByID(User user) {
        ObjectId uId = new ObjectId(user.get_id());
        DBObject query = new BasicDBObject();
        query.put("_id", uId);
        DBCursor cursor = db.find(query);
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            user = UserConverter.toUser(obj);
        }
        return user;
    }

    public List<User> readAllUsers() {
        List<User> users = new ArrayList<User>();
        DBCursor cursor = db.find();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            User h = UserConverter.toUser(obj);
            users.add(h);
        }
        return users;
    }

    public void updateUser(User u) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(u.get_id())).get();
        this.db.update(query, UserConverter.toDBObject(u));
    }

    public void deleteUser(User u) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(u.get_id())).get();
        this.db.remove(query);
    }
}