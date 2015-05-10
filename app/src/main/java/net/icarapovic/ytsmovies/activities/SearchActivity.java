package net.icarapovic.ytsmovies.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.icarapovic.ytsmovies.R;
import net.icarapovic.ytsmovies.api.YTS;

public class SearchActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText searchField;
    private ImageButton clear;
    private RecyclerView recyclerView;
    private String query;
    private Button searchButton;
    private LinearLayoutManager llm;
    private LinearLayout qualityFilter, genreFilter, sortFilter, orderFilter, ratingFilter;
    private AppCompatSpinner qualitySpinner, genreSpinner, sortSpinner, orderSpinner, ratingSpinner;
    private boolean isFilterEnabled;
    private  ArrayAdapter<CharSequence> qualityAdapter, genreAdapter, sortAdapter, orderAdapter, ratingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        init();
        setupListeners();

    }

    private void init(){

        llm = new LinearLayoutManager(this);
        isFilterEnabled = false;

        searchField = (EditText) findViewById(R.id.search);
        clear = (ImageButton) findViewById(R.id.clear);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        qualitySpinner = (AppCompatSpinner) findViewById(R.id.quality);
        genreSpinner = (AppCompatSpinner) findViewById(R.id.genre);
        sortSpinner = (AppCompatSpinner)findViewById(R.id.sort);
        orderSpinner = (AppCompatSpinner)findViewById(R.id.order);
        ratingSpinner = (AppCompatSpinner)findViewById(R.id.rating);
        searchButton = (Button) findViewById(R.id.search_button);

        qualityFilter = (LinearLayout) findViewById(R.id.quality_filter);
        genreFilter = (LinearLayout)findViewById(R.id.genre_filter);
        sortFilter = (LinearLayout) findViewById(R.id.sort_filter);
        orderFilter = (LinearLayout) findViewById(R.id.order_filter);
        ratingFilter = (LinearLayout) findViewById(R.id.rating_filter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);

        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);

        qualityAdapter = ArrayAdapter.createFromResource(this, R.array.quality_name, android.R.layout.simple_spinner_item);
        qualityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualitySpinner.setAdapter(qualityAdapter);

        genreAdapter = ArrayAdapter.createFromResource(this, R.array.genre_name, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        sortAdapter = ArrayAdapter.createFromResource(this, R.array.sort_name, android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        orderAdapter = ArrayAdapter.createFromResource(this, R.array.order_name, android.R.layout.simple_spinner_item);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(orderAdapter);

        ratingAdapter = ArrayAdapter.createFromResource(this, R.array.rating_name, android.R.layout.simple_spinner_item);
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(ratingAdapter);

    }

    private void setupListeners(){

        // on Enter press on keyboard
        searchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                query = searchField.getText().toString();

                if (!isFilterEnabled) {
                    recyclerView.setVisibility(View.VISIBLE);
                    new YTS().searchByQuery(SearchActivity.this, query, recyclerView);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    return true;
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    searchButton.callOnClick();
                    return true;
                }
            }
        });

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    clear.setVisibility(View.VISIBLE);
                else
                    clear.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchField.setText("");
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent(SearchActivity.this, ResultActivity.class);
                result.putExtra("query", query);
                result.putExtra("quality", getResources().getStringArray(R.array.quality_value)[qualitySpinner.getSelectedItemPosition()]);
                result.putExtra("genre", getResources().getStringArray(R.array.genre_value)[genreSpinner.getSelectedItemPosition()]);
                result.putExtra("sort", getResources().getStringArray(R.array.sort_value)[sortSpinner.getSelectedItemPosition()]);
                result.putExtra("order", getResources().getStringArray(R.array.order_value)[orderSpinner.getSelectedItemPosition()]);
                result.putExtra("rating", getResources().getStringArray(R.array.rating_value)[ratingSpinner.getSelectedItemPosition()]);
                startActivity(result);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_filter:
                if(!isFilterEnabled){
                    qualityFilter.setVisibility(View.VISIBLE);
                    genreFilter.setVisibility(View.VISIBLE);
                    sortFilter.setVisibility(View.VISIBLE);
                    orderFilter.setVisibility(View.VISIBLE);
                    ratingFilter.setVisibility(View.VISIBLE);
                    searchButton.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    isFilterEnabled = true;
                    return true;
                }else{
                    qualityFilter.setVisibility(View.GONE);
                    genreFilter.setVisibility(View.GONE);
                    sortFilter.setVisibility(View.GONE);
                    orderFilter.setVisibility(View.GONE);
                    ratingFilter.setVisibility(View.GONE);
                    searchButton.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    isFilterEnabled = false;
                    return true;
                }
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }


        return true;
    }
}
