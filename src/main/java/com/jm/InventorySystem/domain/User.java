package com.jm.InventorySystem.domain;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "users")
public class User {

	@Id
	String _id;
	String fName;
	String sName;
	String type;
    String username;
	@NotNull
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
