package com.example.mim.dhakacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HotelActivity extends AppCompatActivity  implements View.OnClickListener{


private TextView five,four,three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        initToolbar();


        // getSupportActionBar().setTitle(" ");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        five = findViewById(R.id.fi);
        four = findViewById(R.id.fo);
        three=findViewById(R.id.th);

       five.setOnClickListener(this);
       four.setOnClickListener(this);
       three.setOnClickListener(this);

    }

    private void initToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.fi:
                startActivity(new Intent(HotelActivity.this,FishowActivity.class));

                break;

            case R.id.fo:
                startActivity(new Intent(HotelActivity.this,FoShowActivity.class));

                break;

            case R.id.th:
                startActivity(new Intent(HotelActivity.this,ThShowActivity.class));

                break;
        }

    }
}
