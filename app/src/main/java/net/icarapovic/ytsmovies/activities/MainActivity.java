package net.icarapovic.ytsmovies.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.api.YTS;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton fab;
    private boolean isLoading;
    private int page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // check for internet access
        checkConnection();

        // initialize views n shit
        init();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });
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
                return true;
            case R.id.menu_settings:
                return true;
            case R.id.menu_about:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void init(){
        linearLayoutManager = new LinearLayoutManager(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isLoading = false;
        page = 1;

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        new YTS().getRecentMovies(this, recyclerView, page);
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
}
