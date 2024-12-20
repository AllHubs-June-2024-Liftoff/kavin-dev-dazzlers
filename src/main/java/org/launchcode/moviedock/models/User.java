package org.launchcode.moviedock.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Entity
public class User extends AbstractEntity {

    @NotNull(message = "This field is required")
    private String username;

    @NotNull
    private String pwHash;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Review> reviewsList = new ArrayList<>();

    @ManyToMany(mappedBy = "favUser")
    private final List<Movie> favoriteMovies = new ArrayList<>();

    @ManyToMany(mappedBy = "wishUser")
    private final List<Movie> wishMovies = new ArrayList<>();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User () {}

    public User (String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public List<Review> getReviewsList() {
        return reviewsList;
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public List<Movie> getWishMovies() {
        return wishMovies;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}