package org.launchcode.moviedock.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.launchcode.moviedock.data.ApiMovieRepository;
import org.launchcode.moviedock.data.UserRepository;
import org.launchcode.moviedock.models.ApiMovie;
import org.launchcode.moviedock.models.User;
import org.launchcode.moviedock.models.dto.UserMovieDTO;
import org.launchcode.moviedock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ApiMovieController {

    @Autowired
    private ApiMovieRepository apiMovieRepository;



    @PostMapping("movie")
    public String processSearchResults(@ModelAttribute @Valid ApiMovie apiMovie, Model model, @RequestParam String searchTerm) throws JsonProcessingException {

        model.addAttribute("title", "search for a movie");


        apiMovie.setMovieInfo(searchTerm);
        String year = apiMovie.getYear();
        String title = apiMovie.getTitle();
        String director = apiMovie.getDirector();






        model.addAttribute("title", title);
        model.addAttribute("year", year);


        return "movie";
    }


}
