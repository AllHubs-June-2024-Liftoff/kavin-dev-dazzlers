package org.launchcode.moviedock.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Entity
public class ApiMovie extends AbstractEntity{

    //api key
    //private String url = "http://www.omdbapi.com/?apikey=b0901f52&t=";



    //messy code, need to refactor to place relevant data into the fields of the ApiMovie class and to work with teams code
    //testing shows it will pull newest movie, could think about adding an optional year search for remakes.
    //would probably be easier to work with raw json but hey it works for now



    public String getMovieByName(String t) {
        String url = "http://www.omdbapi.com/?apikey=b0901f52&t="+t;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getMovieById(String i){
        String url = "http://www.omdbapi.com/?apikey=b0901f52&t="+i;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }




    private String title;
    private String director;
    private String plot;
    private String poster;
    private String year;
    private String apiID;




    public ApiMovie() {

    }

    public void setMovieInfoByName(String t) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(this.getMovieByName(t));

        this.title = node.get("Title").asText();
        this.director = node.get("Director").asText();
        this.plot = node.get("Plot").asText();
        this.poster = node.get("Poster").asText();
        this.year = node.get("Year").asText();
        this.apiID = node.get("imdbID").asText();
    }

    public void setMovieInfoById(String i) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(this.getMovieById(i));

        this.title = node.get("Title").asText();
        this.director = node.get("Director").asText();
        this.plot = node.get("Plot").asText();
        this.poster = node.get("Poster").asText();
        this.year = node.get("Year").asText();
        this.apiID = node.get("imdbID").asText();
    }


    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }

    public String getYear() {
        return year;
    }

    public String getApiID() {
        return apiID;
    }

    @Override
    public String toString() {
        return "name";
    }
}