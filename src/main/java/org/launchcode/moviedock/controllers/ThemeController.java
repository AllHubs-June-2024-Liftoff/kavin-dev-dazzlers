package org.launchcode.moviedock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThemeController {

    @GetMapping("/test")
    public String home(Model model) {

        String mode = "light";
        String cssPath = "";
        String navMode = "";

        if (mode.equals("dark")) {
            cssPath = "/css/styles.css";
            navMode = "navbar navbar-inverse navbar-fixed-top bar";
        }
        if (mode.equals("light")) {
            cssPath = "/css/styles-light.css";
            navMode = "navbar navbar-default navbar-fixed-top bar";
        }

        model.addAttribute("cssPath", cssPath);
        model.addAttribute("navMode", navMode);

        return "theme-test";
    }
}
