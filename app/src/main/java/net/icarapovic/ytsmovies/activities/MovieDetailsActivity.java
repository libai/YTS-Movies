package net.icarapovic.ytsmovies.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.adapters.DetailsPagerAdapter;
import net.icarapovic.ytsmovies.api.Server;
import net.icarapovic.ytsmovies.models.Actor;
import net.icarapovic.ytsmovies.models.Director;
import net.icarapovic.ytsmovies.models.Movie;
import net.icarapovic.ytsmovies.models.MovieDetails;
import net.icarapovic.ytsmovies.responses.MovieDetailsResponse;
import net.steamcrafted.loadtoast.LoadToast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailsActivity extends AppCompatActivity{

    DetailsPagerAdapter adapter;
    TextView title;
    static int id;
    Bundle infoArgs, castArgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);

        // initialize
        init();
    }

    public void init(){
        id = getIntent().getIntExtra(Movie.ID, 0);
        title = (TextView) findViewById(R.id.title);
        final LoadToast lt = new LoadToast(this);
        lt.setText(Server.FETCHING_DATA)
                .setProgressColor(Color.BLUE)
                .setTranslationY(350)
                .show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(getIntent().getStringExtra(Movie.TITLE));

        final ViewPager pager = (ViewPager) findViewById(R.id.vPager);

        new Server().getMovieDetails(id, new Callback<MovieDetails>() {
            @Override
            public void success(MovieDetails movieDetails, Response response) {
                Actor[] actors = movieDetails.getData().getActors();
                MovieDetailsResponse data = movieDetails.getData();
                int actorNum = actors.length;

                String[] actorNames = new String[actorNum];
                String[] charNames = new String[actorNum];
                String[] actorImages = new String[actorNum];

                int i = 0;
                for(Actor a : actors){
                    actorNames[i] = a.getName();
                    charNames[i] = a.getCharacterName();
                    actorImages[i] = a.getMediumImage();
                    i++;
                }

                String directorName = movieDetails.getData().getDirectors()[0].getName();
                String directorImage = movieDetails.getData().getDirectors()[0].getMediumImage();

                castArgs = new Bundle();
                infoArgs = new Bundle();

                castArgs.putString(Director.NAME, directorName);
                castArgs.putString(Director.MEDIUM_IMAGE, directorImage);
                castArgs.putStringArray(Actor.NAME, actorNames);
                castArgs.putStringArray(Actor.MEDIUM_IMG, actorImages);
                castArgs.putStringArray(Movie.CHARACTERS, charNames);


                infoArgs.putString(Movie.POSTER, data.getImages().getMedium_cover_image());
                infoArgs.putStringArray(Movie.GENRES, data.getGenres());
                infoArgs.putString(Movie.YEAR, data.getYear());
                infoArgs.putString(Movie.RUNTIME, data.getRuntime());
                infoArgs.putString(Movie.DESCRIPTION, data.getDescription_full());
                infoArgs.putString(Movie.IMDB_RATING, data.getRating());
                infoArgs.putString(Movie.RT_RATING, data.getRt_critics_rating());
                infoArgs.putString(Movie.MPA_RATING, data.getMpa_rating());
                infoArgs.putString(Movie.TRAILER, data.getYt_trailer_code());

                adapter = new DetailsPagerAdapter(getSupportFragmentManager(), infoArgs, castArgs);
                pager.setAdapter(adapter);

                lt.success();

            }

            @Override
            public void failure(RetrofitError error) {
                lt.error();
            }
        });
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // roll back transition animation
        supportFinishAfterTransition();
    }

}
