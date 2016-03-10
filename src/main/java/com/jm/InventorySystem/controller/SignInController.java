//Package info.
package com.jm.InventorySystem.controller;

//Import models being used.
import com.jm.InventorySystem.domain.Encrypter;
import com.jm.InventorySystem.domain.User;

import javax.management.Query;

import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

//To get created date.
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Controller
public class SignInController {
 
	RegisterController rc;
	
    @RequestMapping("/hello")
    public String hello(Model model) {
         
        model.addAttribute("greeting", "Hello Spring MVC");
         
        return "helloworld";
         
    }
    
    @RequestMapping("/signin")
    public String signin(Model model,
    		User userDetails) {
 	  	    	
    	if(userDetails.getUsername() == null) {
    		return "signIn";
    	} else {

    	System.out.println(userDetails.getUsername() + " - " + userDetails.getPassword());

		//Creates a Encryption object that encrypts the password.
		Encrypter e = new Encrypter(userDetails.getPassword());
		String dPass = e.encrypt();
     	System.out.println("Users encrypted password is: " + dPass);


    	// Since 2.10.0, uses MongoClient
    	MongoClient mongo = new MongoClient( "localhost" , 27017 );

    	DB db = mongo.getDB("InventorySys");

    	DBCollection table = db.getCollection("users");
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("username", userDetails.getUsername());
    	searchQuery.put("password", dPass);

    	DBCursor cursor = table.find(searchQuery);

    	while (cursor.hasNext()) {
    		//Success
    		System.out.println(cursor.next());
    		return "redirect:homepage";
    	}

    	System.out.println("not recognised");

    	return "signIn";
    	}
    }
    



}
