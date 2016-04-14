package com.jm.InventorySystem.domain;

import java.util.Date;

/**
 * Created by James on 14/04/2016.
 */
public class Asset {

    String id;
    String aname;
    Integer qty;
    String description;
    String cid;
    long value;
    Date purchased;
    Date dateCreated;

    //No Arg Constructor
    public Asset() {

    }

    public Asset(String i, String a, Integer q, String d, String c, long v, Date p, Date date){
        this.id = i;
        this.aname = a;
        this.qty = q;
        this.description = d;
        this.cid = c;
        this.value = v;
        this.purchased = p;
        this.dateCreated = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
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
}
