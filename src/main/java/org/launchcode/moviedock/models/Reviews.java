package org.launchcode.moviedock.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Reviews extends AbstractEntity{

    @ManyToOne
    @NotNull(message= "select movie to review")
    private Movies movie;


    @ManyToOne
    @NotNull
    private User user;

    @NotBlank
    private String review_text;

//    provide a dropdown to select options from 1-5
    private int star_rating;

    public Reviews(Movies movie, User user, String review_text, int star_rating) {
        this.movie = movie;
        this.user = user;
        this.review_text = review_text;
        this.star_rating = star_rating;
    }

    public Reviews() {
    }

    public Movies getMovie() {
        return movie;
    }

    public User getUser() {
        return user;
    }

    public String getReview_text() {
        return review_text;
    }

    public int getStar_rating() {
        return star_rating;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public void setStar_rating(int star_rating) {
        this.star_rating = star_rating;
    }
}
