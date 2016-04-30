package com.jm.InventorySystem.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

/**
 * Created by James on 14/04/2016.
 */
@Document(collection = "assets")
public class Asset {

    @Id
    String _id;
    String aname;
    Integer qty;
    String description;
    String type;
    String cid;
    float value;
    String purchString;
    Date purchased;
    Date dateCreated;
    String additionalInfo;
    String depreciation;
    String dateToUpdate;

    //No Arg Constructor
    public Asset() {

    }

    public Asset(String i, String a, Integer q, String d, String t, String c, Float f, Date p, Date date){
        this._id = i;
        this.aname = a;
        this.qty = q;
        this.description = d;
        this.type = t;
        this.cid = c;
        this.value = f;
        this.purchased = p;
        this.dateCreated = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getPurchString() {
        return purchString;
    }

    public void setPurchString(String purchString) {
        this.purchString = purchString;
    }

    public Date getPurchased() {
        return purchased;
    }

    public void setPurchased(Date purchased) {
        this.purchased = purchased;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(String depreciation) {
        this.depreciation = depreciation;
    }

    public String getDateToUpdate() {
        return dateToUpdate;
    }

    public void setDateToUpdate(String dateToUpdate) {
        this.dateToUpdate = dateToUpdate;
    }
}
