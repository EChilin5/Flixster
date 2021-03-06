package com.codepath.echilin.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.echilin.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    private static final String Youtube_Api_Key = "AIzaSyA6Ge8rvYFZeqCOY4ql1HlEgdjX554Ir_I";
    private static final String Videos_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);
        youTubePlayerView = findViewById(R.id.player);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverView());
        ratingBar.setRating((float)movie.getRating());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(Videos_URL, movie.getMovieID()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                   JSONArray results = json.jsonObject.getJSONArray("results");
                   if(results.length() == 0){
                       return;
                   }
                   String youtubeKey = results.getJSONObject(0).getString("key");
                   Log.d("DetailActivity", youtubeKey);
                   initializeYoutube(youtubeKey, movie.getRating());

                } catch (JSONException e) {
                    Log.e("DetailActivity", "Failed to parse Json", e);

                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });


    }

    private void initializeYoutube(String youtubeKey, double rating) {
        youTubePlayerView.initialize(Youtube_Api_Key, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "Success");
                // do any work here to cue video, play video, etc.
                if(rating > 5){
                    youTubePlayer.loadVideo(youtubeKey);
                }else{
                    youTubePlayer.cueVideo(youtubeKey);

                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "Fai;ure");
            }
        });
    }
}