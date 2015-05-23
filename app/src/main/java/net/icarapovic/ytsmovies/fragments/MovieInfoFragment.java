package net.icarapovic.ytsmovies.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.api.Server;
import net.icarapovic.ytsmovies.models.Movie;
import net.icarapovic.ytsmovies.models.MovieDetails;
import net.icarapovic.ytsmovies.responses.MovieDetailsResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieInfoFragment extends Fragment {

    MovieDetailsResponse m;

    public static MovieInfoFragment newInstance(int id) {
        MovieInfoFragment fragment = new MovieInfoFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_info, container, false);

        final ImageView poster = (ImageView) v.findViewById(R.id.poster);
        final TextView genre = (TextView) v.findViewById(R.id.genre);
        final TextView year = (TextView) v.findViewById(R.id.year);
        final TextView runtime = (TextView) v.findViewById(R.id.runtime);
        final TextView imdb = (TextView) v.findViewById(R.id.imdb);
        final TextView rt_rating = (TextView) v.findViewById(R.id.rotten_tomatoes);
        final TextView mpa = (TextView) v.findViewById(R.id.mpa);
        final TextView desc = (TextView) v.findViewById(R.id.description);
        final Button youtube = (Button) v.findViewById(R.id.youtube);

        new Server().getMovieDetails(getArguments().getInt("id"), new Callback<MovieDetails>() {
            @Override
            public void success(MovieDetails movieDetails, Response response) {
                m = movieDetails.getData();

                Picasso.with(getActivity().getApplicationContext()).load(m.getImages().getLarge_cover_image()).into(poster);
                if(m.getGenres().length > 1)
                    genre.setText(m.getGenres()[0]);
                else
                    genre.setText(m.getGenres()[0] + " / " + m.getGenres()[1]);
                year.setText(m.getYear());
                runtime.setText(m.getRuntime() + " min");
                imdb.setText(m.getRating() + " / 10");
                rt_rating.setText("RT: " + m.getRt_critics_rating());
                mpa.setText("MPA: " + m.getMpa_rating());
                desc.setText(m.getDescription_full());


                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + m.getYt_trailer_code()));
                            startActivity(i);
                        }catch (ActivityNotFoundException ex){
                            Intent i = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://www.youtube.com/watch?v=" + m.getYt_trailer_code()));
                            startActivity(i);
                        }
                    }
                });

                youtube.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Error receiving data", Toast.LENGTH_LONG).show();
            }
        });


        return v;
    }


}
