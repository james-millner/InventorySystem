//Package info.
package com.jm.InventorySystem.controller;

//Import models being used. 
import com.jm.InventorySystem.domain.User;
import com.jm.InventorySystem.domain.Encrypter;

//Importing spring framework
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//Importing mongo tools.
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import javax.validation.Valid;


@Controller
public class RegisterController {
	
	//Registration method.
	@RequestMapping("/register")
    public String register(@ModelAttribute("userModel")
                           @Valid User userDetails,
                           BindingResult bindingResult) {
    	
    	//Just reached the page, if null stop.
    	if(userDetails.getfName() == null) {
    		//Just reached the page. Show the registration area.
    		return "register";
    	} else {
            if (bindingResult.hasErrors()) {
                return "register";
            } else {


                System.out.println(userDetails.getfName() + " - " + userDetails.getsName());
                System.out.println(userDetails.getUsername() + "-" + userDetails.getPassword() + "-" + userDetails.getType());

                //Make an encryption object with the users password.
                Encrypter ec = new Encrypter(userDetails.getPassword());
                String ecPass = ec.encrypt();
                //System.out.println("** ENCRYPTER: Original Password: " + userDetails.getPassword() + " Encrypted Password is:" + ecPass);

                // Since 2.10.0, uses MongoClient
                MongoClient mongo = new MongoClient("localhost", 27017);
                DB db = mongo.getDB("InventorySys");
                DBCollection table = db.getCollection("users");

                //Check the username doesn't already exist.
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("username", userDetails.getUsername());

                DBCursor cursor = table.find(searchQuery);

                while (cursor.hasNext()) {
                    //Success
                    System.out.println(cursor.next());
                    return "redirect:signInError";
                }

                //Everything is OK. Write data to Mongo.
                BasicDBObject document = new BasicDBObject();
                document.put("fname", userDetails.getfName());
                document.put("sname", userDetails.getsName());
                document.put("username", userDetails.getUsername());
                document.put("password", ecPass);
                document.put("type", userDetails.getType());
                table.insert(document);

                System.out.println(ec.decrypt());
                return "redirect:signin";
            }
        }

   }
    @RequestMapping("/signInError")
    public String error() {return "signInUsernameError";}

    public void checkUsername(String uname) {}

	

}

