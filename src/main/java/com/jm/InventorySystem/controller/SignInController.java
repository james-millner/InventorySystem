//Package info.
package com.jm.InventorySystem.controller;

//Import models being used.
import com.jm.InventorySystem.converter.UserConverter;
import com.jm.InventorySystem.domain.Encrypter;
import com.jm.InventorySystem.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;



@Controller
public class SignInController {

    @RequestMapping("/signin")
    public String signin(Model model,
    					 User userDetails,
						 HttpServletResponse response) {
 	  	    	
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
			User u = UserConverter.toUser(cursor.next());
			Cookie cookie = new Cookie("user", u.getUsername() + "-" + u.getType());
			cookie.setMaxAge(10000);
			response.addCookie(cookie);
    		return "redirect:homepage";
    	}

    	System.out.println("not recognised");

    	return "signIn";
    	}
    }
    



}
