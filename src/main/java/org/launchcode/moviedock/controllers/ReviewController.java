package org.launchcode.moviedock.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.launchcode.moviedock.data.AppUserRepository;
import org.launchcode.moviedock.data.MovieRepository;
import org.launchcode.moviedock.data.ReviewRepository;
import org.launchcode.moviedock.models.AppUser;
import org.launchcode.moviedock.models.Movie;
import org.launchcode.moviedock.models.Review;
import org.launchcode.moviedock.models.dto.UserReviewDTO;
import org.launchcode.moviedock.security.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReviewController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PrincipalService principalService;

    private String reviewError = "false";

    @GetMapping("review")
    public String displayReviewForm(@RequestParam Integer movieId,HttpServletRequest request, Model model){
        Optional<Movie> value = movieRepository.findById(movieId);
        Movie movie = value.get();
        AppUser user = principalService.getPrincipal().get();
        Review review = reviewRepository.findByUserIdAndMovieId(movie.getId(),user.getId());

        UserReviewDTO userReview =new UserReviewDTO();
        userReview.setMovie(movie);
        userReview.setUser(user);


        if(reviewError.equals("true")){
            model.addAttribute("errorMsg","Review cannot be empty.");
            reviewError = "false";
        }

        if(review != null){
            userReview.setReview_text(review.getReview_text());
            userReview.setStar_rating(review.getStar_rating());


            model.addAttribute("userReview", userReview);
            model.addAttribute("dispText","You have already reviewed '" + movie.getName() + "'. You can update your old review.");

            return "review/addReview.html";
        }
        else{

            model.addAttribute("userReview", userReview);
            model.addAttribute("dispText","Enter your review for " + movie.getName());

            return "review/addReview.html";
        }


    }

    @PostMapping("review")
    public String saveMovieReview(@ModelAttribute @Valid UserReviewDTO userReview,
                                  Errors errors, HttpServletRequest request,
                                  Model model) {


        if (errors.hasErrors()) {

            reviewError = "true";

            return "redirect:review?movieId=" + userReview.getMovie().getId();

        } else {

            Movie movie = userReview.getMovie();
            AppUser user = principalService.getPrincipal().get();

            Review review = reviewRepository.findByUserIdAndMovieId(movie.getId(), user.getId());

            if (review != null) {

                review.setReview_text(userReview.getReview_text());
                review.setStar_rating(userReview.getStar_rating());
                reviewRepository.save(review);
            } else {

                Review newReview = new Review();
                newReview.setReview_text(userReview.getReview_text());
                newReview.setStar_rating(userReview.getStar_rating());
                newReview.setMovie(movie);
                newReview.setUser(user);
                reviewRepository.save(newReview);
            }


            model.addAttribute("user", user);
//            model.addAttribute("reviews",reviewRepository.findByUserId(user.getId()));
//            model.addAttribute("movies",movieRepository.findAllById(reviewRepository.findMovieByUserId(user.getId())));
            return "user/profile.html";
        }


    }


}
