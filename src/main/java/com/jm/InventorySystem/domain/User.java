package com.jm.InventorySystem.domain;

import java.util.Date;

public class User {
	
	String id;
	String fName;
	String sName;
	String type;
    String username;
	String password;
	Date dateCreated;
	
	public User() {
		//No Arg Constructor
	}
	
	public User(String fn, String sn, String t, String u, String p, Date d) {
		this.fName = fn;
		this.sName = sn;
		this.type = t;
		this.username = u;
		this.password = p;
		this.dateCreated = d;
	}

	public void setId(String i) {this.id = i; }
	public void setFName(String n) { this.fName = n; };
	public void setSName(String s) { this.sName = s; };
	public void setType(String t) { this.type = t; };
	public void setUsername(String a) { this.username = a; }
	public void setPassword(String p) { this.password = p; }
	public void setDateCreated(Date d) {
		this.dateCreated = d;
	}

	public String getId() { return this.id; }
	public String getfName(){ return this.fName; }
	public String getsName(){ return this.sName; }
	public String getType(){ return this.type; }
	public String getUsername(){ return this.username; }
	public String getPassword() { return this.password; }
	public Date getDateCreated() { return this.dateCreated; }
	

	
}
