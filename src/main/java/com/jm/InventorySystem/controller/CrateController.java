package com.jm.InventorySystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by James on 31/03/2016.
 */
@Controller
public class CrateController {

    @RequestMapping("/crates")
    public String Crates() {
        return "/main/crates";
    }
}
