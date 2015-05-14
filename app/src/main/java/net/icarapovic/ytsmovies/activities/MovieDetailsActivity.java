package net.icarapovic.ytsmovies.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.api.YTS;
import net.icarapovic.ytsmovies.models.MovieDetails;
import net.icarapovic.ytsmovies.responses.MovieDetailsResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailsActivity extends AppCompatActivity{

    TextView title, year, genre, duration, mpa, rating, director;
    TextView actor1, actor2, actor3, actor4, language, downloads, likes, rt_rating;
    TextView quality, size, description, framerate;
    ImageView poster;
    Button youtube;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);

        init();

        new YTS().getMovieDetails(id, new Callback<MovieDetails>() {
            @Override
            public void success(MovieDetails movieDetails, Response response) {
                doTheMagic(movieDetails.getData());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Error receiving data", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init(){
        id = getIntent().getIntExtra("id", 0);
        title = (TextView) findViewById(R.id.title);
        year = (TextView) findViewById(R.id.year);
        genre = (TextView) findViewById(R.id.genre);
        duration = (TextView) findViewById(R.id.runtime);
        mpa = (TextView) findViewById(R.id.mpa);
        poster = (ImageView)findViewById(R.id.poster);
        director = (TextView) findViewById(R.id.director);
        rating = (TextView) findViewById(R.id.rating);
        actor1 = (TextView) findViewById(R.id.actor1);
        actor2 = (TextView) findViewById(R.id.actor2);
        actor3 = (TextView) findViewById(R.id.actor3);
        actor4 = (TextView) findViewById(R.id.actor4);
        language = (TextView) findViewById(R.id.language);
        downloads = (TextView) findViewById(R.id.downloads);
        likes = (TextView) findViewById(R.id.likes);
        rt_rating = (TextView) findViewById(R.id.rt_rating);
        quality = (TextView) findViewById(R.id.quality);
        size = (TextView) findViewById(R.id.size);
        description = (TextView) findViewById(R.id.description);
        framerate = (TextView) findViewById(R.id.framerate);
        youtube = (Button) findViewById(R.id.yt);
   }

    public void doTheMagic(final MovieDetailsResponse data){
        title.setText(data.getTitle());
        year.setText("Year: " + data.getYear());
        duration.setText("Runtime: " + data.getRuntime() + " min");
        mpa.setText("MPA Rating: " + data.getMpa_rating());
        rating.setText("IMDb rating: " + data.getRating() + " / 10");
        director.setText("Directed by: " + data.getDirectors()[0].getName());
        language.setText(data.getLanguage());
        downloads.setText(data.getDownload_count() + " times");
        likes.setText(data.getLike_count() + " times");
        rt_rating.setText(data.getRt_critics_rating());
        description.setText(data.getDescription_full());
        framerate.setText(data.getTorrents()[0].getFramerate() + " fps");

        if(data.getTorrents().length > 2)
            quality.setText("720p/1080p/3D");
        else if(data.getTorrents().length > 1)
            quality.setText("720p/1080p");
        else
            quality.setText(data.getTorrents()[0].getQuality());

        if(data.getTorrents().length > 1)
            size.setText(data.getTorrents()[0].getSize() + " (720p) / " + data.getTorrents()[1].getSize() + " (1080p/3D)");
        else
            size.setText(data.getTorrents()[0].getSize() + " (" + quality.getText() + ")");

        if(data.getGenres().length > 1)
            { genre.setText(data.getGenres()[0] + " / " + data.getGenres()[1]); }
        else
            { genre.setText(data.getGenres()[0]); }

        Picasso.with(getApplicationContext()).load(data.getImages().getMedium_cover_image()).into(poster);

        actor1.setText(data.getActors()[0].getName() + " (" + data.getActors()[0].getCharacterName() + ")");
        actor2.setText(data.getActors()[1].getName() + " (" + data.getActors()[1].getCharacterName() + ")");
        actor3.setText(data.getActors()[2].getName() + " (" + data.getActors()[2].getCharacterName() + ")");
        actor4.setText(data.getActors()[3].getName() + " (" + data.getActors()[3].getCharacterName() + ")");

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + data.getYt_trailer_code()));
                    startActivity(i);
                }catch (ActivityNotFoundException ex){
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + data.getYt_trailer_code()));
                    startActivity(i);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        supportFinishAfterTransition();
    }
}
