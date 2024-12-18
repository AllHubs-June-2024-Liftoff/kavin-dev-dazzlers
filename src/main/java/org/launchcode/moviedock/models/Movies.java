package org.launchcode.moviedock.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Movies extends AbstractEntity{

    @NotNull(message = "Movie name is needed")
    private String name;

    private int search_count;

    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Reviews> reviewsList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "favorites")
    private final List<User> favUser = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "wishList")
    private final List<User> wishUser = new ArrayList<>();

    public Movies(String name, int search_count) {
        this.name = name;
        this.search_count = search_count;
    }

    public Movies() {
    }

    public String getName() {
        return name;
    }

    public int getSearch_count() {
        return search_count;
    }

    public void setSearch_count(int search_count) {
        this.search_count = search_count;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    @Override
    public String toString() {
        return name;
    }
}
