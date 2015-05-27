package net.icarapovic.ytsmovies.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.adapters.DetailsPagerAdapter;
import net.icarapovic.ytsmovies.api.Server;
import net.icarapovic.ytsmovies.fragments.MovieInfoFragment;
import net.icarapovic.ytsmovies.fragments.NewestFragment;
import net.icarapovic.ytsmovies.fragments.UpcomingFragment;
import net.icarapovic.ytsmovies.models.Movie;
import net.icarapovic.ytsmovies.models.MovieDetails;
import net.icarapovic.ytsmovies.responses.MovieDetailsResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailsActivity extends AppCompatActivity{

    DetailsPagerAdapter adapter;
    TextView title;
    static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);

        // initialize
        init();
    }

    public void init(){
        id = getIntent().getIntExtra("id", 0);
        title = (TextView) findViewById(R.id.title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(getIntent().getStringExtra("title"));

        ViewPager pager = (ViewPager) findViewById(R.id.vPager);
        adapter = new DetailsPagerAdapter(getSupportFragmentManager(), id);
        pager.setAdapter(adapter);
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // roll back transition animation
        supportFinishAfterTransition();
    }

}
