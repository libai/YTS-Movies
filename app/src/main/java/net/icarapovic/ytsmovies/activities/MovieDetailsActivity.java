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

    PagerAdapter adapter;
    TextView title;
    static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details);

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
        adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        supportFinishAfterTransition();
    }

    public static class PagerAdapter extends FragmentPagerAdapter {

        private static int ITEM_COUNT = 1;
        public PagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return MovieInfoFragment.newInstance(id);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return ITEM_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return "Information";
                default:
                    return "Unknown";
            }
        }
    }
}
