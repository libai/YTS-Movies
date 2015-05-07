package net.icarapovic.ytsmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.models.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private Movie[] movies;
    private Context c;

    public MovieListAdapter(Context c, Movie[] movies){
        this.movies = movies;
        this.c = c;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "Click", Toast.LENGTH_SHORT).show();
            }
        });

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.title.setText(movies[position].getTitle());
        holder.year.setText("Year: " + movies[position].getYear());
        holder.rating.setText("Rating: " + movies[position].getRating());
        Picasso.with(c).load(movies[position].getMedium_cover_image()).into(holder.poster);

        if(movies[position].getGenres().length > 1)
            { holder.genre.setText(movies[position].getGenres()[0] + " / " + movies[position].getGenres()[1]); }
        else
            { holder.genre.setText(movies[position].getGenres()[0]); }
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


        public MovieViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            genre = (TextView) itemView.findViewById(R.id.genre);
            rating = (TextView) itemView.findViewById(R.id.rating);
            poster = (ImageView) itemView.findViewById(R.id.artwork);

        }
    }
}
