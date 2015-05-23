package net.icarapovic.ytsmovies.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import net.icarapovic.ytsmovies.R;

public class SearchActivity extends AppCompatActivity {

    private EditText searchField;
    private ImageButton clear;
    private FloatingActionButton searchButton;
    private AppCompatSpinner genreSpinner, sortSpinner,ratingSpinner;
    private InputMethodManager imm;
    private RadioGroup qualityRG, orderRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        init();
        setupListeners();
    }

    private void init(){

        searchField = (EditText) findViewById(R.id.search);
        clear = (ImageButton) findViewById(R.id.clear);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        genreSpinner = (AppCompatSpinner) findViewById(R.id.genre);
        sortSpinner = (AppCompatSpinner)findViewById(R.id.sort);
        ratingSpinner = (AppCompatSpinner)findViewById(R.id.rating);
        searchButton = (FloatingActionButton) findViewById(R.id.search_button);
        qualityRG = (RadioGroup) findViewById(R.id.quality_rg);
        orderRG = (RadioGroup) findViewById(R.id.order_rg);

        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);

        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this, R.array.genre_name, R.layout.dropdown_display);
        genreAdapter.setDropDownViewResource(R.layout.dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(this, R.array.sort_name, R.layout.dropdown_display);
        sortAdapter.setDropDownViewResource(R.layout.dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        ArrayAdapter<CharSequence> ratingAdapter = ArrayAdapter.createFromResource(this, R.array.rating_name, R.layout.dropdown_display);
        ratingAdapter.setDropDownViewResource(R.layout.dropdown_item);
        ratingSpinner.setAdapter(ratingAdapter);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void setupListeners(){

        // on Enter press on keyboard
        searchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                imm.hideSoftInputFromInputMethod(v.getWindowToken(), 0);
                genreSpinner.requestFocus();
                searchButton.callOnClick();
                return true;
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
                result.putExtra("query", searchField.getText().toString());
                result.putExtra("quality", qualityRG.getCheckedRadioButtonId() == R.id.q_720p ? "720p" : qualityRG.getCheckedRadioButtonId() == R.id.q_1080p ? "1080p" : "3D");
                result.putExtra("genre", getResources().getStringArray(R.array.genre_value)[genreSpinner.getSelectedItemPosition()]);
                result.putExtra("sort", getResources().getStringArray(R.array.sort_value)[sortSpinner.getSelectedItemPosition()]);
                result.putExtra("order", orderRG.getCheckedRadioButtonId() == R.id.asc ? "asc" : "desc");
                result.putExtra("rating", getResources().getStringArray(R.array.rating_value)[ratingSpinner.getSelectedItemPosition()]);
                startActivity(result);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        supportFinishAfterTransition();
    }
}
