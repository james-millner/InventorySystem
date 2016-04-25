package com.jm.InventorySystem.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.domain.Statistics;
import com.mongodb.*;
import com.mongodb.util.JSON;


/**
 * Created by James on 23/04/2016.
 */
public class MongoDBStatsDAO {

    public DBCollection dbInvStats;
    public DBCollection dbAstStats;
    public DBCollection dbAstValue;

    public MongoDBStatsDAO(MongoClient mongoClient) {
        this.dbInvStats = mongoClient.getDB("InventorySys").getCollection("invstats");
        this.dbAstStats = mongoClient.getDB("InventorySys").getCollection("aststats");
        this.dbAstValue = mongoClient.getDB("InventorySys").getCollection("astvalue");
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


}
