package com.jm.InventorySystem.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by James on 21/04/2016.
 */
@Document(collection = "invtypes")
public class InventoryType {

    @Id
    String _id;
    String type;

    //NoArgs
    public InventoryType() {

    }

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
