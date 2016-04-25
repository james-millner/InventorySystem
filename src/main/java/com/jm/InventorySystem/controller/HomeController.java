package com.jm.InventorySystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServlet;

/**
 * Created by James on 02/03/2016.
 */

@Controller
public class HomeController extends HttpServlet {

    @RequestMapping("/homepage")
    public String homepage() {
        return "homepage";
    }


}
