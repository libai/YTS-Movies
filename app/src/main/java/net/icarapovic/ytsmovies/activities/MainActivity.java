package net.icarapovic.ytsmovies.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.adapters.MainPagerAdapter;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // check for internet access
        checkConnection();

        // initialize views
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
        int id = item.getItemId();

        switch(id){
            case R.id.menu_login:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                // TODO - functional login panel
                return true;
            case R.id.menu_settings:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                // TODO - settings page
                return true;
            case R.id.menu_about:
                Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                // TODO - show app and library info
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init(){

        // initialize objects and reference views
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set up fragments
        ViewPager pager = (ViewPager) findViewById(R.id.vPager);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    private void checkConnection(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] info = cm.getAllNetworkInfo();
        boolean hasConnection = false;

        // if connection detected - set it to true
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
                            // retry button
                            checkConnection();
                        }
                    })
                    .setNegativeButton(R.string.network_error_negative_button, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // exit button
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

}
