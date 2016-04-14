package com.jm.InventorySystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.DAO.MongoDBAssetDAO;
import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.domain.Asset;
import com.jm.InventorySystem.domain.Crate;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
@Controller
public class AssetController {

    @RequestMapping("/assets")
    public String Assets(Model model,
                         Asset asset) {
        ObjectMapper mapper = new ObjectMapper();
        // Since 2.10.0, uses MongoClient
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongoClient);
        List<Crate> crates = crateDAO.readAllCrate();
        model.addAttribute("crateList", crates);
        mongoClient.close();

        MongoClient mongoAsset = new MongoClient("localhost", 27017);
        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongoAsset);

        List<Asset> assets = assetDAO.readAllAssets();
        System.out.println("ASSETS = " + assets.size());
        if(asset.getAname() == null) {
            return "/main/assets";
        } else {


            try {
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date purchasedFormatted = df.parse(asset.getPurchString().replaceAll("-", "/"));
                Date today = new Date();
                asset.setDateCreated(today);
                asset.setPurchased(purchasedFormatted);
                //ASSET CREATED.
                //System.out.println(asset.getPo() + "-" + asset.getPe() + "-" + asset.getPurchased().toString());
                assetDAO.createAsset(asset);
                mongoAsset.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return "/main/assets";
        }

    }
}
