package com.example.mim.dhakacity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivityFiveBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FiveActivity extends AppCompatActivity implements StarAdapter.HotelUpdateDeleteListener {

    private ActivityFiveBinding binding;
    private StarAdapter adapter;
    private LinearLayoutManager llm;

    private DatabaseReference rootRef;
    private DatabaseReference userRef;


    private List<Star>hotellist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil. setContentView(this,R.layout.activity_five);

        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       llm=new LinearLayoutManager(this);
       llm.setOrientation(LinearLayoutManager.VERTICAL);

        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Restaurants");
       adapter = new StarAdapter(this,hotellist);
       binding.recylerview.setLayoutManager(llm);
       binding.recylerview.setAdapter(adapter);
      userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotellist.clear();
                Toast.makeText(FiveActivity.this, "data changed", Toast.LENGTH_SHORT).show();
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

    }

    public void addHotel(View view) {

        String id = userRef.push().getKey();
        String name =binding.hotelsname.getText().toString();
        String address =binding.hotelsadd.getText().toString();
        String contact =binding.hotelscon.getText().toString();

        Star star = new Star(id,name,address,contact);
        userRef.child(id).setValue(star).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(FiveActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                binding.hotelsname.setText("");
                binding.hotelsadd.setText("");
                binding.hotelscon.setText("");
            }
        });
    }

    @Override
    public void onHotelUpdate(String hotelId) {

    }

    @Override
    public void onHotelDelete(String hotelId) {
        userRef.child(hotelId).removeValue();

    }

    public void ViewData(View view) {

        switch (view.getId()){
            case R.id.show1:
                startActivity(new Intent(FiveActivity.this,FishowActivity.class));
                break;
        }
    }

}
