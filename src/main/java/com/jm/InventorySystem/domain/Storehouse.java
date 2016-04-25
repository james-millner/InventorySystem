package com.jm.InventorySystem.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Created by James on 02/03/2016.
 */
@Document(collection = "storehouses")
public class Storehouse {


    @Id
    private String _id;
    private String name;
    private int size;
    private String access;
    private String address;
    private boolean owned;
    private boolean rented;
    private boolean active;

    public Storehouse() {
        //No Arg Constructor.
    }

    public Storehouse(String n, int s, String a, String ad, boolean so, boolean r, boolean act) {
        //Full Arg Constructor.
        this.name = n;
        this.size = s;
        this.access = a;
        this.address = ad;
        this.owned = so;
        this.rented = r;
        this.active = act;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
