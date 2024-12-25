package org.launchcode.moviedock.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/403")
    public String test() {
        return "error/403";
    }

    @GetMapping("/settings")
    public String settings() {
        return "profile/settings";
    }

    @GetMapping("signup")
    public String signupForm() {
        return "profile/signup";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile/profile-page";
    }

    @GetMapping("/signout")
    public String signout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/user/signin";
    }
}
