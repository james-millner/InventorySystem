package com.jm.InventorySystem.domain;

import java.util.Date;

/**
 * Created by James on 14/04/2016.
 */
public class Inventory {

    String id;
    String iname;
    Integer qty;
    String description;
    String cid;
    Date dateCreated;

    //No Arg Constructor
    public Inventory() {

    }

    public Inventory(String i, String in, Integer q, String d, String c, Date date) {
        this.id = i;
        this.iname = in;
        this.qty = q;
        this.description = d;
        this.cid = c;
        this.dateCreated = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
