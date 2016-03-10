package com.jm.InventorySystem.domain;

import com.sun.istack.internal.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	
	String fName;
	String sName;
	String type;
	@NotNull
	@Size(min=5, max=20)
    String username;
	@NotNull
	@Size(min=8, max=16)
	String password;
	
	public User() {
		//No Arg Constructor
	}
	
	public User(String fn, String sn, String t, String u, String p) {
		this.fName = fn;
		this.sName = sn;
		this.type = t;
		this.username = u;
		this.password = p;
	}
	
	public void setfName(String n) { this.fName = n; };
	public void setSName(String s) { this.sName = s; };
	public void setType(String t) { this.type = t; };
	public void setUsername(String a) { this.username = a; }
	public void setPassword(String p) { this.password = p; }
	
	public String getfName(){ return this.fName; }
	public String getsName(){ return this.sName; }
	public String getType(){ return this.type; }
	public String getUsername(){ return this.username; }
	public String getPassword() { return this.password; }
	

	
}
