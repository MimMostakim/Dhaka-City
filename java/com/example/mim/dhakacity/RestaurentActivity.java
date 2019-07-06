package com.example.mim.dhakacity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivityRestaurentBinding;

public class RestaurentActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityRestaurentBinding binding;

    private TextView Dhanmondi,gulshan,banani,uttara,Puran,mirpur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_restaurent);
        //getSupportActionBar().setTitle(" ");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initToolbar();

        Dhanmondi=findViewById(R.id.dhan);
        gulshan=findViewById(R.id.gul);
        banani=findViewById(R.id.ban);
        uttara=findViewById(R.id.utt);
        Puran=findViewById(R.id.puran);
        mirpur=findViewById(R.id.mir);

        Dhanmondi.setOnClickListener(this);
        gulshan.setOnClickListener(this);
        banani.setOnClickListener(this);
        uttara.setOnClickListener(this);
        Puran.setOnClickListener(this);
        mirpur.setOnClickListener(this);
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

            case R.id.dhan:
                startActivity(new Intent(RestaurentActivity.this,DhanmondiActivity.class));
                break;

            case R.id.gul:
                startActivity(new Intent(RestaurentActivity.this,GulshanActivity.class));
                break;

            case R.id.ban:
                startActivity(new Intent(RestaurentActivity.this,BananiActivity.class));
                break;

            case R.id.utt:
                startActivity(new Intent(RestaurentActivity.this,UttaraActivity.class));
                break;

            case R.id.puran:
                startActivity(new Intent(RestaurentActivity.this,PuranDhakaActivity.class));
                break;

            case R.id.mir:
                startActivity(new Intent(RestaurentActivity.this,MirpurActivity.class));
                break;


        }
    }
}


