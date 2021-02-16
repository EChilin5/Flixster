 package com.codepath.echilin.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.echilin.flixster.DetailActivity;
import com.codepath.echilin.flixster.R;
import com.codepath.echilin.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");

        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);

        //Get teh movie at the passed in postion
        Movie movie = movies.get(position);

        //bind the movie data into the VH
        holder.bind(movie);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvtitle;
        TextView tvOverView;
        TextView releaseDateRating;
        ImageView ivPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.ivTitle);
            tvOverView = itemView.findViewById(R.id.tvOverView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            releaseDateRating = itemView.findViewById(R.id.tvDate);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            tvtitle.setText(movie.getTitle());
            tvOverView.setText(movie.getOverView());
            releaseDateRating.setText("Release: " + movie.getDate() + "\nRating: " + movie.getRating() );
            String imageUrl;
            //if phone is landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            }else{
                imageUrl = movie.getPosterPath();
            }
            //then imageUrl = back drop image
            //else imageUrl = posterimage
            Glide.with(context).load(imageUrl).into(ivPoster);
            // register click listener om the whole container
            // Navigate to a new activity on tap


            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);

                }
            });
        }
    }
}
