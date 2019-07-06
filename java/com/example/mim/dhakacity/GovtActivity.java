package com.example.mim.dhakacity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivityGovtBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GovtActivity extends AppCompatActivity implements HospitAdapter.HospitalUpdateDeleteListener {
    private ActivityGovtBinding binding;
    private HospitAdapter adapter;
    private LinearLayoutManager llm;

    private DatabaseReference rootRef;
    private DatabaseReference userRef;

    private List<Hospit>hospitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_govt);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Govt Hospital");
        adapter = new HospitAdapter(this,hospitList);
        binding.recylerview.setLayoutManager(llm);
        binding.recylerview.setAdapter(adapter);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hospitList.clear();
                Toast.makeText(GovtActivity.this, "data changed", Toast.LENGTH_SHORT).show();
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

    public void addHospital(View view) {
        String id = userRef.push().getKey();
        String name =binding.hospitalnames.getText().toString();
        String Address = binding.hospitaladd.getText().toString();
        String Contact = binding.hospitalcon.getText().toString();

        Hospit hospit = new Hospit(id,name,Address,Contact);
        userRef.child(id).setValue(hospit).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(GovtActivity.this, "Saved", Toast.LENGTH_SHORT).show();
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

       /* final Hospit hospit = new Hospit(hospitalId,name,address,contact);

        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Govt Hospital");
        hospitList = new ArrayList<>();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()){
                    Hospit hos = d.getValue(Hospit.class);
                    //hospitList.add(hos);




                }


                AlertDialog.Builder builder = new AlertDialog.Builder(GovtActivity.this);
                View v = LayoutInflater.from(GovtActivity.this).inflate(R.layout.single_information_details, null);
                //TextView id = v.findViewById(R.id.detailsid);
                TextView NameTv = v.findViewById(R.id.details_name);
                TextView addressTv = v.findViewById(R.id.details_address);
                TextView contactTv = v.findViewById(R.id.details_contact);
                builder.setView(v);
               // id.setText(hospit.getId());
                NameTv.setText(hospit.getHospitalsName());
                addressTv.setText(hospit.getHospitalAddress());
                contactTv.setText(hospit.getHospitalContanctNo());
                builder.setTitle("Info");
                builder.setNegativeButton("Cancel", null);
                builder.show();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/





    }



    public void fivedata(View view) {

        switch (view.getId()){
            case R.id.show4:
                startActivity(new Intent(GovtActivity.this,GovShowActivity.class));
                break;
        }
    }
}
