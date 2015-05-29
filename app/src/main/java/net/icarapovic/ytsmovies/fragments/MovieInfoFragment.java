package net.icarapovic.ytsmovies.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.models.Movie;
import net.icarapovic.ytsmovies.responses.MovieDetailsResponse;

public class MovieInfoFragment extends Fragment {

    MovieDetailsResponse m;
    Bundle args;
    public static final String NAME = "Information";

    public static MovieInfoFragment newInstance(Bundle args) {
        MovieInfoFragment fragment = new MovieInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        args = getArguments();
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

                Picasso.with(getActivity().getApplicationContext()).load(args.getString(Movie.POSTER)).into(poster);

                if(args.getStringArray(Movie.GENRES).length == 1)
                    genre.setText(args.getStringArray(Movie.GENRES)[0]);
                else
                    genre.setText(args.getStringArray(Movie.GENRES)[0] + " / " + args.getStringArray(Movie.GENRES)[1]);

                year.setText(args.getString(Movie.YEAR));
                runtime.setText(args.getString(Movie.RUNTIME) + " min");
                imdb.setText(args.getString(Movie.IMDB_RATING) + " / 10");
                rt_rating.setText("RT: " + args.getString(Movie.RT_RATING));
                mpa.setText("MPA: " + args.getString(Movie.MPA_RATING));
                desc.setText(args.getString(Movie.DESCRIPTION));

                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + args.getString(Movie.TRAILER)));
                            startActivity(i);
                        }catch (ActivityNotFoundException ex){
                            Intent i = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://www.youtube.com/watch?v=" + args.getString(Movie.TRAILER)));
                            startActivity(i);
                        }
                    }
                });

                youtube.setVisibility(View.VISIBLE);

        return v;
    }


}
