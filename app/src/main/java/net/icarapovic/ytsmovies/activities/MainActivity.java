package net.icarapovic.ytsmovies.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.adapters.MovieListAdapter;
import net.icarapovic.ytsmovies.api.Server;
import net.icarapovic.ytsmovies.fragments.NewestFragment;
import net.icarapovic.ytsmovies.fragments.UpcomingFragment;
import net.icarapovic.ytsmovies.models.ListMovies;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // check for internet access
        checkConnection();

        // initialize views n shit
        init();

        // let there be interaction!
        setupListeners();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.menu_login:
                Toast.makeText(this, "Nope...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_settings:
                Toast.makeText(this, "Nope again...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_about:
                Toast.makeText(this, "To be implemented...", Toast.LENGTH_SHORT).show();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void init(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.vPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Server server = new Server();
        server.getRecentMovies(1, new Callback<ListMovies>() {
            @Override
            public void success(ListMovies listMovies, Response response) {
                recyclerView.setAdapter(new MovieListAdapter(MainActivity.this, listMovies.getListMoviesResponse().getMovies()));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Error receiving data", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkConnection(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] info = cm.getAllNetworkInfo();
        boolean hasConnection = false;

        for(NetworkInfo n : info){
            if(n.isConnected() && !hasConnection)
                hasConnection = true;
        }

        if(!hasConnection){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.network_error_title)
                    .setMessage(R.string.network_error_body)
                    .setPositiveButton(R.string.network_error_positive_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkConnection();
                        }
                    })
                    .setNegativeButton(R.string.network_error_negative_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create();
            builder.show();
        }
    }

    private void setupListeners(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, fab, "fab");
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i, options.toBundle());
            }
        });

    }

    public static class PagerAdapter extends FragmentPagerAdapter {

        private static int ITEM_COUNT = 2;
        public PagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return NewestFragment.newInstance();
                case 1:
                    return UpcomingFragment.newInstance();
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
                    return "Newest";
                case 1:
                    return "Upcoming";
                default:
                    return "Unknown" + position;
            }
        }
    }
}
