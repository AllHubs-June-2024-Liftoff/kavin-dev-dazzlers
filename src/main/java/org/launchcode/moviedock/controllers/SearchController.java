package org.launchcode.moviedock.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.launchcode.moviedock.data.ApiMovieRepository;
import org.launchcode.moviedock.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;




@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ApiMovieRepository apiMovieRepository;


    @PostMapping("results")
    public String processSearchResults(@ModelAttribute @Valid ApiMovie apiMovie, Model model, @RequestParam String searchTerm) throws JsonProcessingException {

        model.addAttribute("title", "search for a movie");


        apiMovie.setMovieInfoByName(searchTerm);
        String year = apiMovie.getYear();
        String title = apiMovie.getTitle();
        String apiId = apiMovie.getApiID();






        model.addAttribute("title", title);
        model.addAttribute("year", year);
        model.addAttribute("apiId", apiId);

        return "search";
    }

    @GetMapping("movie/{title}")
    public String displayViewMovies(Model model, @PathVariable int title) {
        Optional<ApiMovie> optApi = apiMovieRepository.findById(title);
        if (optApi.isPresent()){
            ApiMovie apiMovie = (ApiMovie) optApi.get();
            model.addAttribute("apiMovie", apiMovie);
            return "movie";
        }
        else {
            return "redirect:../";
        }



        //return "view";
    }




}
