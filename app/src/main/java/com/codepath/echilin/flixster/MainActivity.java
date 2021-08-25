package com.codepath.echilin.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.echilin.flixster.adapters.MovieAdapter;
import com.codepath.echilin.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";

    List<Movie> movies;
    MovieAdapter movieAdapter;
    ImageView ivTopImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        ivTopImage = findViewById(R.id.ivTopPick);

        movies =  new ArrayList<>();

        // Create an Adapter
        movieAdapter = new MovieAdapter(this, movies);

        // Set the adapter on the Recycler view
        rvMovies.setAdapter(movieAdapter);

        // set a layout Manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        fetchData();


    }

    private void ivSetUpTopPick() {
        Movie movie = movies.get(0);
        String imageUrl;
        //if phone is landscape
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageUrl = movie.getBackdropPath();
        }else{
            imageUrl = movie.getPosterPath();
        }
        //then imageUrl = back drop image
        //else imageUrl = posterimage
//        int radius = 30; // corner radius, higher value = more rounded
//        int margin = 10;
        Glide.with(this).load(imageUrl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivTopImage);
    }


    private void fetchData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results =  jsonObject.getJSONArray("results");
                    Log.i(TAG, "Results: " + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    Collections.sort(movies, new CompareMovies());
                    Collections.reverse(movies);

                    movieAdapter.notifyDataSetChanged();
                    ivSetUpTopPick();

                    Log.i(TAG, "Movies: " + movies);
                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception hit",e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    private class CompareMovies implements Comparator<Movie> {
        @Override
        public int compare(Movie o1, Movie o2) {
            Integer movie1 = (int)o1.getRating();
            Integer movie2 = (int)o2.getRating();

            return movie1.compareTo(movie2);
        }
    }
}