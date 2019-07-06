package com.example.mim.dhakacity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivityPrivateBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PrivateActivity extends AppCompatActivity implements HospitAdapter.HospitalUpdateDeleteListener {

    private ActivityPrivateBinding binding;
    private HospitAdapter adapter;
    private LinearLayoutManager llm;

    private DatabaseReference rootRef;
    private DatabaseReference userRef;

    private List<Hospit>hospitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil. setContentView(this,R.layout.activity_private);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Private Hospital");
        adapter = new HospitAdapter(this,hospitList);
        binding.recylerview.setLayoutManager(llm);
        binding.recylerview.setAdapter(adapter);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hospitList.clear();
                Toast.makeText(PrivateActivity.this, "data changed", Toast.LENGTH_SHORT).show();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Hospit hospit = d.getValue(Hospit.class);
                    hospitList.add(hospit);
                }
                adapter.updateList(hospitList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addprivateHospital(View view) {

        String id = userRef.push().getKey();
        String name =binding.hospitalnames.getText().toString();
        String Address = binding.hospitaladd.getText().toString();
        String Contact = binding.hospitalcon.getText().toString();

        Hospit hospit = new Hospit(id,name,Address,Contact);
        userRef.child(id).setValue(hospit).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(PrivateActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                binding.hospitalnames.setText("");
                binding.hospitaladd.setText("");
                binding.hospitalcon.setText("");
            }
        });
    }

    @Override
    public void onHospitalUpdate(String hospitalId) {

    }

    @Override
    public void onHospitalDelete(String hospitalId) {

       // userRef.child(hospitalId).removeValue();

    }

    @Override
    public void onSingleDataFetching(String hospitalId, String name, String address, String contact) {

    }


    public void fourdata(View view) {

        switch (view.getId()){
            case R.id.show5:
                startActivity(new Intent(PrivateActivity.this,PriShowActivity.class));
                break;
        }
    }
}

