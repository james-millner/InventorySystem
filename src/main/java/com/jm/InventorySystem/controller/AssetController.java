package com.jm.InventorySystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jm.InventorySystem.DAO.*;
import com.jm.InventorySystem.domain.*;
import com.mongodb.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by James on 14/04/2016.
 */
@Controller
public class AssetController {

    @RequestMapping("/assets")
    public String Assets(Model model) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Set up access to generate inventory stats.
        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongoClient);
        MongoDBAssetTypeDAO assetTypeDAO = new MongoDBAssetTypeDAO(mongoClient);

        //Clear Asset stats.
        MongoDBStatsDAO statsDAO = new MongoDBStatsDAO(mongoClient);
        statsDAO.dbAstStats.drop();

        //Generate new Stats.
        List<AssetType> astTypes = assetTypeDAO.readAllTypes();
        List<Statistics> stats = new ArrayList<Statistics>();
        for (int i = 0; i < astTypes.size(); i++) {
            String type = astTypes.get(i).getType();
            long value = assetDAO.countType(type);
            Statistics stat = new Statistics();
            stat.setName(type);
            stat.setValue(value);
            stats.add(stat);
            statsDAO.createAstStat(stat);
        }
        model.addAttribute("stats", stats);

        List<Asset> byDate = assetDAO.sortByDate();
        model.addAttribute("asstByDate", byDate);

        List<Statistics> assetStats = new ArrayList<Statistics>();
        //Reuse byDate list to make a new List.
        //Determining value of each type of asset.
        for(int c = 0; c < astTypes.size(); c++) {
            AssetType a = astTypes.get(c);
            Statistics stat = new Statistics();
            stat.setName(a.getType());
            int value = 0;
            for(int cd = 0; cd < byDate.size(); cd++) {
                Asset got = byDate.get(cd);
                if(got.getType().equals(a.getType())){
                    value += got.getValue();
                }
            }
            stat.setValue(value);
            assetStats.add(stat);
            System.out.println(stat.getName() + "-" + stat.getValue());
        }

        model.addAttribute("valueStats", assetStats);
        System.out.println(assetStats.size());

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

        //Get all asset types for the view.
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBAssetTypeDAO assetTypeDAO = new MongoDBAssetTypeDAO(mongo);
        List<AssetType> ats = assetTypeDAO.readAllTypes();
        model.addAttribute("types", ats);
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
        List<AssetType> ats = assetTypeDAO.readAllTypes();
        model.addAttribute("types", ats);
        if (type.getType() == null) {
            return "/main/Assets/settings";
        } else {
            assetTypeDAO.createType(type);
            return "redirect:/assets/settings";
        }

    }

    @RequestMapping("/assets/deleteType")
    public String delType(@RequestParam("type") String type, Model model) {
        MongoClient mAsset = new MongoClient("localhost", 27017);
        //Get AssetTypes.
        MongoDBAssetTypeDAO assetTypeDAO = new MongoDBAssetTypeDAO(mAsset);
        List<AssetType> ats = assetTypeDAO.readAllTypes();
        model.addAttribute("types", ats);

        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mAsset);

        Asset asset = new Asset();
        AssetType aType = new AssetType();
        asset.setType(type);
        List<Asset> assets = assetDAO.getAssetsByType(asset);
        if (assets.size() > 0) {
            model.addAttribute("bool", "true");
            return "/main/Assets/settings";
        } else
            aType.setType(type);
            aType = assetTypeDAO.getType(aType);
            assetTypeDAO.deleteType(aType);
            return "redirect:/assets/settings";

    }



    @RequestMapping("/assets/viewAll")
    public String viewAllAssets(Model model,
                                Asset asset) {
        MongoClient mongoAssets = new MongoClient("localhost", 27017);
        MongoDBAssetDAO assetDAO = new MongoDBAssetDAO(mongoAssets);
        List<Asset> assetList = assetDAO.readAllAssets();
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

        //Get all asset types for the view.
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDBAssetTypeDAO assetTypeDAO = new MongoDBAssetTypeDAO(mongo);
        List<AssetType> ats = assetTypeDAO.readAllTypes();
        model.addAttribute("types", ats);

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
