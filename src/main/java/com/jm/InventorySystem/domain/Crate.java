package com.jm.InventorySystem.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

/**
 * Created by James on 31/03/2016.
 */
@Document(collection = "crates")
public class Crate {

    @Id
    String _id;
    String sid;
    String cName;
    String crateNote;
    Integer width;
    Integer height;
    Date dateCreated;

    //No Arg constructor
    public Crate() {

    }

    public Crate(String i, String c, int w, int h, Date d) {
        this._id = i;
        this.cName = c;
        this.width = w;
        this.height = h;
        this.dateCreated = d;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCrateNote() {
        return crateNote;
    }

    public void setCrateNote(String crateNote) {
        this.crateNote = crateNote;
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

