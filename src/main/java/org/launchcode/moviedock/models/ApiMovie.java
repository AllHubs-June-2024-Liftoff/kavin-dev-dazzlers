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


    public String getMovieInfo(String t) {
        try {
            URL url = new URL("http://www.omdbapi.com/?apikey=b0901f52&t="+t);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // or "POST", etc.

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response
                return response.toString();
            } else {
                return "GET request failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "blah";
    }

    public String getMovie(String t) {
        String url = "http://www.omdbapi.com/?apikey=b0901f52&t="+t;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }






    private String title;
    private String director;
    private String plot;
    private String poster;
    private String year;




    public ApiMovie() {

    }

    public void setMovieInfo(String t) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(this.getMovie(t));

        this.title = node.get("Title").asText();
        this.director = node.get("Director").asText();
        this.plot = node.get("Plot").asText();
        this.poster = node.get("Poster").asText();
        this.year = node.get("Year").asText();



    }




    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }


    public String getInfo() {
        return getMovieInfo(this.title);
    }



    @Override
    public String toString() {
        return "name";
    }
}