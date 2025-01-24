package org.launchcode.moviedock.controllers;


import org.launchcode.moviedock.data.AppUserRepository;
import org.launchcode.moviedock.models.AppUser;
import org.launchcode.moviedock.security.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@RequestMapping("/")
@Controller
public class SearchUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    private PrincipalService principalService;


    @GetMapping("user_search")
    public String userSearch() {

        return "userSearch";
    }

    @PostMapping("user_search")
    public String listSearchResults(Model model, @RequestParam String searchName) {

        if (searchName.isEmpty()) {
            model.addAttribute("blankField", "Please Enter a name to search");
            return "userSearch";
        } else {

            List<AppUser> usersList = appUserRepository.findByUsernameLike(searchName);

            if (usersList.isEmpty()) {
                model.addAttribute("noMatch", "No user found with name '" + searchName + "'");
                return "userSearch";

            } else {
                model.addAttribute("usersList", usersList);
                return "userSearch";
            }
        }

    }

    @GetMapping("searched_profile")
    public String userSearchFound(Model model, @RequestParam int userId) {

        Optional<AppUser> appUser = appUserRepository.findById(userId);
        AppUser userFound = appUser.get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication != null && authentication.isAuthenticated()) {
        if (authentication != null && !authentication.toString().contains("anonymous")) {
            //            return "User is logged in";
            AppUser loggedInUser = principalService.getPrincipal();
            if(!userFound.equals(loggedInUser)){
                model.addAttribute("selfProfile","false");
                model.addAttribute("user",userFound);
                return "user/profile";
            }else{
                model.addAttribute("selfProfile","true");
                model.addAttribute("user",userFound);
                return "user/profile";
            }

        } else {
//            return "User is not logged in";
            model.addAttribute("selfProfile","false");
            model.addAttribute("user",userFound);
            return "user/profile";

        }

//        model.addAttribute("user",userFound);
//        return "user/profile";
    }


}