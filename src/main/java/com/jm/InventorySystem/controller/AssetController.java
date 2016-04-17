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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String Assets() {
        return "/main/assets";

    }

    @RequestMapping("/assets/addAsset")
    public String addAsset(Model model,
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
        if (asset.getAname() == null) {
            return "/main/Assets/addAssets";
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/main/Assets/addAssets";
        }
    }

    @RequestMapping("/assets/viewAll")
    public String viewAllAssets(Model model,
                                Asset asset) {
        MongoClient mongoAssets = new MongoClient("localhost", 27017);
        MongoDBAssetDAO inventoryDAO = new MongoDBAssetDAO(mongoAssets);
        List<Asset> assetList = inventoryDAO.readAllAssets();
        model.addAttribute("assetList", assetList);
        mongoAssets.close();

        return "/main/Assets/viewall";
    }

    @RequestMapping("/assets/viewAsset")
    public String viewAsset(Model model,
                            Asset asset,
                            @RequestParam("_id")String id) {
        MongoClient mongoAsset = new MongoClient("localhost", 27017);
        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongoAsset);
        asset.set_id(id);
        asset = assetDAO.getAsset(asset);
        model.addAttribute("asset", asset);

        return "/main/Assets/viewAsset";
    }

    @RequestMapping("/assets/del")
    public String delAsset(Asset asset,
                           @RequestParam("_id") String id) {
        MongoClient mongoAsset = new MongoClient("localhost", 27017);
        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongoAsset);
        asset.set_id(id);
        assetDAO.deleteAsset(asset);
        return "redirect:/assets/viewAll";
    }
}
