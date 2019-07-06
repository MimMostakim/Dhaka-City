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
import android.view.View;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivityMirpurBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MirpurActivity extends AppCompatActivity implements RestaurantAdapter.RestaurantUpdateDeleteListener {

    private ActivityMirpurBinding binding;
    private RestaurantAdapter adapter;
    private LinearLayoutManager llm;

    private DatabaseReference rootref;
    private  DatabaseReference userRef;

    private List<Rest> restList=new ArrayList<>();
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil. setContentView(this,R.layout.activity_mirpur);
        //getSupportActionBar().setTitle(" ");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rootref= FirebaseDatabase.getInstance().getReference();
        userRef=rootref.child("Mirpur");
        userRef.keepSynced(true);
        adapter=new RestaurantAdapter(this,restList);
        binding.recyclerView.setLayoutManager(llm);
        binding.recyclerView.setAdapter(adapter);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                restList.clear();
                for (DataSnapshot d:dataSnapshot.getChildren()){
                    Rest rest = d.getValue(Rest.class);
                    restList.add(rest);
                }
                adapter.updatrList(restList);
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
        searchView.setQueryHint(getString(R.string.search_restaurant_name));
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
    public void onrestaurantUpdate(String restaurantId) {

    }

    @Override
    public void onrestaurantDelete(String resturantId) {

        userRef.child(resturantId).removeValue();

    }

    @Override
    public void onsingleDatafetched(String restaurentId, String name, String Address) {

    }
}
