package org.launchcode.moviedock.controllers;


import org.launchcode.moviedock.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.launchcode.moviedock.models.User;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("search")
public class SearchUserController {

@Autowired
UserRepository userRepository;

    @PostMapping("")
    public String listSearchResults(Model model, @RequestParam String searchName){

        if(searchName.isEmpty()){
            model.addAttribute("blankField","Enter name to search");
            return "search";
        }else {

            List<User> usersList = userRepository.findByUsernameLike(searchName);
            model.addAttribute("usersList", usersList);
            return "search";
        }

    }


}
