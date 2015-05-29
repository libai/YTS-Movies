package net.icarapovic.ytsmovies.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.activities.MovieDetailsActivity;
import net.icarapovic.ytsmovies.models.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Movie[] movies;
    private Activity activity;

    public MovieListAdapter(Activity a, Movie[] movies){
        this.movies = movies;
        this.activity = a;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_card, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        holder.title.setText(movies[position].getTitle());
        holder.year.setText("Year: " + movies[position].getYear());
        holder.rating.setText("Rating: " + movies[position].getRating());
        Picasso.with(activity).load(movies[position].getMedium_cover_image()).into(holder.poster);
        holder.runtime.setText("Runtime: " + movies[position].getRuntime() + " min");

        if(movies[position].getGenres().length > 1)
            { holder.genre.setText(movies[position].getGenres()[0] + " / " + movies[position].getGenres()[1]); }
        else
            { holder.genre.setText(movies[position].getGenres()[0]); }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, MovieDetailsActivity.class);
                i.putExtra(Movie.ID, movies[position].getId());
                i.putExtra(Movie.TITLE, movies[position].getTitle());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, holder.poster, "poster");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }


    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        protected TextView title;
        protected TextView year;
        protected TextView genre;
        protected TextView rating;
        protected ImageView poster;
        protected TextView runtime;


        public MovieViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            genre = (TextView) itemView.findViewById(R.id.genre);
            rating = (TextView) itemView.findViewById(R.id.rating);
            poster = (ImageView) itemView.findViewById(R.id.artwork);
            runtime = (TextView) itemView.findViewById(R.id.duration);
        }
    }



}

