package net.icarapovic.ytsmovies.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.api.Server;
import net.icarapovic.ytsmovies.models.UpcomingMovies;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UpcomingFragment extends Fragment{

    public static UpcomingFragment newInstance(){
        UpcomingFragment mf = new UpcomingFragment();
        return mf;
    }

    public static String getTitle(){
        return "Upcoming";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upcoming, container, false);
        final ImageView i1 = (ImageView) v.findViewById(R.id.i1);
        final ImageView i2 = (ImageView) v.findViewById(R.id.i2);
        final ImageView i3 = (ImageView) v.findViewById(R.id.i3);
        final ImageView i4 = (ImageView) v.findViewById(R.id.i4);

        new Server().getUpcomingMovies(new Callback<UpcomingMovies>() {
            @Override
            public void success(final UpcomingMovies upcomingMovies, Response response) {
                Picasso.with(getActivity().getApplicationContext()).load(upcomingMovies.getData().getMovies()[0].getMedium_cover_image()).into(i1);
                Picasso.with(getActivity().getApplicationContext()).load(upcomingMovies.getData().getMovies()[1].getMedium_cover_image()).into(i2);
                Picasso.with(getActivity().getApplicationContext()).load(upcomingMovies.getData().getMovies()[2].getMedium_cover_image()).into(i3);
                Picasso.with(getActivity().getApplicationContext()).load(upcomingMovies.getData().getMovies()[3].getMedium_cover_image()).into(i4);

                i1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://www.imdb.com/title/" + upcomingMovies.getData().getMovies()[0].getImdb_code())
                        );

                        startActivity(i);
                    }
                });

                i2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://www.imdb.com/title/" + upcomingMovies.getData().getMovies()[1].getImdb_code())
                        );

                        startActivity(i);
                    }
                });

                i3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://www.imdb.com/title/" + upcomingMovies.getData().getMovies()[2].getImdb_code())
                        );

                        startActivity(i);
                    }
                });

                i4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://www.imdb.com/title/" + upcomingMovies.getData().getMovies()[3].getImdb_code())
                        );

                        startActivity(i);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Error receiving data", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
