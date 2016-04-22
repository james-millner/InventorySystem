package com.jm.InventorySystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.DAO.MongoDBAssetDAO;
import com.jm.InventorySystem.DAO.MongoDBAssetTypeDAO;
import com.jm.InventorySystem.DAO.MongoDBCrateDAO;
import com.jm.InventorySystem.DAO.MongoDBStorehouseDAO;
import com.jm.InventorySystem.domain.Asset;
import com.jm.InventorySystem.domain.AssetType;
import com.jm.InventorySystem.domain.Crate;
import com.jm.InventorySystem.domain.Storehouse;
import com.mongodb.*;
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

    @RequestMapping("/assets/settings")
    public String assetSettings(Model model,
                                AssetType type) {
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBAssetTypeDAO assetTypeDAO = new MongoDBAssetTypeDAO(mongo);
        if(type.getType() == null) {
            return "/main/Assets/settings";
        } else {
            assetTypeDAO.createType(type);
            return "redirect:/assets/settings";
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
        mongoAsset.close();

        String cid = asset.getCid();
        if (cid == null) {
            model.addAttribute("na", "N/A");
            return "/main/Assets/viewAsset";
        } else {
            //Get Crate Info.
            MongoClient mongoCrate = new MongoClient("localhost", 27017);
            MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongoCrate);
            Crate c = new Crate();
            c.set_id(cid);
            c = crateDAO.getCrate(c);
            model.addAttribute("crate", c);
            mongoCrate.close();

            //Get Storehouse Info.
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDBStorehouseDAO storehouseDAO = new MongoDBStorehouseDAO(mongoClient);
            Storehouse h = new Storehouse();
            h.set_id(c.getSid());
            h = storehouseDAO.readStorehouse(h);
            model.addAttribute("storehouse", h);

            return "/main/Assets/viewAsset";
        }
    }

    @RequestMapping("/assets/viewAsset/update")
    public String updateAsset(Model model,
                              Asset asset,
                              @RequestParam("_id") String id) {
        MongoClient mongoAsset = new MongoClient("localhost", 27017);
        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongoAsset);

        Asset blank = new Asset();
        blank.set_id(id);
        blank = assetDAO.getAsset(blank);

        Date date = blank.getDateCreated();

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDBCrateDAO crateDAO = new MongoDBCrateDAO(mongoClient);
        if(asset.getAname() == null) {

            asset.set_id(id);
            asset = assetDAO.getAsset(asset);
            model.addAttribute("asset", asset);
            mongoAsset.close();

            List<Crate> crates = crateDAO.readAllCrate();
            model.addAttribute("crateList", crates);
            mongoClient.close();
            return "/main/Assets/viewAssetUpdate";
        } else {
            asset.set_id(id);
            try {
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date purchasedFormatted = df.parse(asset.getPurchString().replaceAll("-", "/"));
                asset.setPurchased(purchasedFormatted);
                asset.setDateCreated(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assetDAO.updateAsset(asset);
            return "redirect:/assets/viewAsset?_id=" + id;
        }

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
