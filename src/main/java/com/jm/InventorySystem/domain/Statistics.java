package com.jm.InventorySystem.domain;

/**
 * Created by James on 22/04/2016.
 */
public class Statistics {

    String name;
    long value;

    //No Arg
    public Statistics() {

    }

    public Statistics(String n, long v) {
        this.name = n;
        this.value = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
