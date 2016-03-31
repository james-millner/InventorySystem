package com.jm.InventorySystem.domain;

import java.util.Date;

/**
 * Created by James on 31/03/2016.
 */
public class Crate {

    String id;
    String cName;
    Integer width;
    Integer height;
    Date dateCreated;

    //No Arg constructor
    public Crate() {

    }
    public Crate(String i, String c, int w, int h, Date d) {
        this.id = i;
        this.cName = c;
        this.width = w;
        this.height = h;
        this.dateCreated = d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}