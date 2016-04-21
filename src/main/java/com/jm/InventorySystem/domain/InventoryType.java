package com.jm.InventorySystem.domain;

/**
 * Created by James on 21/04/2016.
 */
public class InventoryType {

    String _id;
    String type;

    public InventoryType(String t){
        this.type = t;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
