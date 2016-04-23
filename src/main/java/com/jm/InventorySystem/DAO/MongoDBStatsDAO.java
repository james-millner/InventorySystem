package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Statistics;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.BasicQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 23/04/2016.
 */
public class MongoDBStatsDAO {

    public DBCollection dbInvStats;
    public DBCollection dbAstStats;

    public MongoDBStatsDAO(MongoClient mongoClient) {
        this.dbInvStats = mongoClient.getDB("InventorySys").getCollection("invstats");
        this.dbAstStats = mongoClient.getDB("InventorySys").getCollection("aststats");
    }

    public void createInvStat(Statistics type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String JSONStat = mapper.writeValueAsString(type);
            DBObject dbObject = (DBObject) JSON.parse(JSONStat);
            this.dbInvStats.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAstStat(Statistics type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String JSONStat = mapper.writeValueAsString(type);
            DBObject dbObject = (DBObject) JSON.parse(JSONStat);
            this.dbAstStats.insert(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readAllInvTypes() {
        ObjectMapper mapper = new ObjectMapper();
        BasicDBObject where = new BasicDBObject();
        where.put("_id", 0);
        where.put("name", 1);
        where.put("value", 1);
        String result = "";
        try {
            DBCursor cursor = dbInvStats.find(new BasicDBObject(), where);
            JSON json = new JSON();
            result = "{ \"Types\": " + json.serialize(cursor) + "}";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
