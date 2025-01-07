package org.launchcode.moviedock.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Entity
public class ApiMovie extends AbstractEntity{

    //api key
    private String url = "http://www.omdbapi.com/?apikey=b0901f52&t=";



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





    private String title;
    private String director;
    private String year;
    private String info;



    public ApiMovie() {


    }


    public void setTitle(String title) {
        this.title = title;
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