package com.example.mim.dhakacity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivityAuthinticateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthinticateActivity extends AppCompatActivity {
    private ActivityAuthinticateBinding binding;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private View parent_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_authinticate);
        parent_view = findViewById(android.R.id.content);


        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        this.setTitle("Sign In Activity");

        //getSupportActionBar().setTitle(" ");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((View) findViewById(R.id.forgot_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Forgot Password", Snackbar.LENGTH_SHORT).show();
            }
        });





    }

    public void loginUser(View view) {

        //String email = binding.emailInput.getText().toString();
      //  String pass = binding.passwordInput.getText().toString();

        switch (view.getId()){
            case
             R.id.signin:
                UserLogin();
            break;
        }

    }

    private void UserLogin() {

        String email = binding.emailInput.getText().toString();
        String pass = binding.passwordInput.getText().toString();

        if(email.isEmpty())
        {
            binding.emailInput.setError("Enter an email address");
            binding.passwordInput.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.emailInput.setError("Enter a valid email address");
            binding.passwordInput.requestFocus();
            return;
        }
        //checking the validity of the password
        if(email.isEmpty())
        {
            binding.emailInput.setError("Enter a password");
            binding.passwordInput.requestFocus();
            return;
        }

        if (pass.length()<6){
            binding.passwordInput.setError("Minimum length of a password should be 6");
            binding.passwordInput.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(AuthinticateActivity.this,MainActivity.class);
                    Toast.makeText(AuthinticateActivity.this, "You are Logged In", Toast.LENGTH_SHORT).show();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(AuthinticateActivity.this, "Login Unsuccessfully ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }






   /* public void logoutUser(View view) {

        if(currentUser != null){
            firebaseAuth.signOut();
            binding.statusTV.setText("logged out");
        }
    }*/

    public void signup(View view) {

        switch (view.getId()){
            case R.id.signuptextview:
                startActivity(new Intent(AuthinticateActivity.this,SignUpActivity.class));
                break;
        }
    }
}

