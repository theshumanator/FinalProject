package com.example.displayjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayJokeActivity extends AppCompatActivity {

    /*
        I was going to use butterknife but given it's only 2 textviews,
         I decided it wont be worth it
     */
    TextView tvJoke;
    TextView tvLaugh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent()!=null) {
            if (getIntent().hasExtra(getString(R.string.joke))) {
                String joke = getIntent().getStringExtra(getString(R.string.joke));
                tvJoke=findViewById(R.id.tv_joke);
                tvLaugh=findViewById(R.id.tv_joke_label);
                tvJoke.setText(joke);
                tvLaugh.setVisibility(View.VISIBLE);
            } else {
                showErrorToast();
            }
        } else {
            showErrorToast();
        }
    }

    private void showErrorToast() {
        tvLaugh.setVisibility(View.GONE);
        String error=getString(R.string.error);
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    public void goBack(View view) {
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return true;
    }

}
