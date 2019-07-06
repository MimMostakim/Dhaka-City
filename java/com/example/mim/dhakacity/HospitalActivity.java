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

public class HospitalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView govt,pri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        initToolbar();

//       getSupportActionBar().setTitle(" ");
      // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      govt= findViewById(R.id.tv_origin);
       pri=findViewById(R.id.tv_destination);

       govt.setOnClickListener(this);
       pri.setOnClickListener(this);
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

            case R.id.tv_origin:
                startActivity(new Intent(HospitalActivity.this,GovShowActivity.class));
                break;

            case R.id.tv_destination:
                startActivity(new Intent(HospitalActivity.this,PriShowActivity.class));
                break;


        }

    }
}
