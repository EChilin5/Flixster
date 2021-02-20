package com.codepath.echilin.flixster.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overView;
    String date;
    double rating;
    int movieID;

    // empty constructor needed by the Parceler library
    public Movie(){

    }

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overView = jsonObject.getString("overview");
        date = jsonObject.getString("release_date");
        rating = jsonObject.getInt("vote_average");
        movieID =jsonObject.getInt("id");


    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies =  new ArrayList<>();
        for(int i = 0; i< movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return movies;
    }
    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getPosterPath() {
        //podterpath will replace %s
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getpartialOverView() {
        String[] newOverView = overView.split(" ");
        StringBuilder description = new StringBuilder();
        for(int i = 0; i < 15; i++ ){
            description.append(" ").append(newOverView[i]);
        }
        description.append(" ...");
        return description.toString();
    }
    public String getOverView(){
        return overView;
    }


    public String getDate(){
        return date;
    }
    public double getRating(){
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }
}
