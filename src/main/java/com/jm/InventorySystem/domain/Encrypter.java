package com.jm.InventorySystem.domain;

import java.security.*;
import javax.crypto.*;

public class Encrypter {

	String password = null;


	public Encrypter(String pass) {
		this.password = pass;

	}

	public String getPass()  { return this.password; }

	public String encrypt() {

        //Encrypt the password by 10 unicode places.
        String passResult = "";
		int a = this.password.length();
		char ps;
		for(int i = 0; i < a; i++) {
            ps = this.password.charAt(i);
            ps += 10;
            passResult += ps;
		}
        this.password = passResult;

        String result = "";
        result = passResult;

        //System.out.println("ENCRYPTED PASS IS = " + result);

        return result;
	}

	public  String decrypt() {
        //Encrypt the password by 10 unicode places.
        String passResult = "";
        int a = this.password.length();
        char ps;
        for(int i = 0; i < a; i++) {
            ps = this.password.charAt(i);
            ps -= 10;
            passResult += ps;
        }
        this.password = passResult;

        String result = "";
        result = passResult;

        //System.out.println("DECRYPTED PASS IS = " + result);

        return result;
    }

}
