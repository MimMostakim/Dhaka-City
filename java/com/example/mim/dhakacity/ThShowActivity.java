package com.example.mim.dhakacity;

import android.app.SearchManager;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivityThShowBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ThShowActivity extends AppCompatActivity implements StarAdapter.HotelUpdateDeleteListener {

    private ActivityThShowBinding binding;

    private StarAdapter adapter;
    private LinearLayoutManager llm;

    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private List<Star> hotellist = new ArrayList<>();

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_th_show);
        //getSupportActionBar().setTitle(" ");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("ThreeStar Restaurants");
        userRef.keepSynced(true);
        adapter = new StarAdapter(this, hotellist);
        binding.recylerView.setLayoutManager(llm);
        binding.recylerView.setAdapter(adapter);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotellist.clear();
                //Toast.makeText(FiveActivity.this, "data changed", Toast.LENGTH_SHORT).show();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Star star = d.getValue(Star.class);
                    hotellist.add(star);
                }
                adapter.updateList(hotellist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        initToolbar();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setQueryHint(getString(R.string.search_hotel));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onHotelUpdate(String hotelId) {

    }

    @Override
    public void onHotelDelete(String hotelId) {

    }
}
