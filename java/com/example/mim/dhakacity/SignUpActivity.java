package com.example.mim.dhakacity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mim.dhakacity.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbarid);
        this.setTitle("Sign Up Activity");
    }




    public void Signup(View view) {

        switch (view.getId()){
            case R.id.sign:
                userRegister();
                break;
        }


    }

    private void userRegister() {

        String email = binding.emailinput.getText().toString();
        String pass = binding.passwordinput.getText().toString();

        if(email.isEmpty())
        {
            binding.emailinput.setError("Enter an email address");
            binding.passwordinput.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.emailinput.setError("Enter a valid email address");
            binding.passwordinput.requestFocus();
            return;
        }
        //checking the validity of the password
        if(email.isEmpty())
        {
            binding.emailinput.setError("Enter a password");
        binding.passwordinput.requestFocus();
        return;
        }

        if (pass.length()<6){
            binding.passwordinput.setError("Minimum length of a password should be 6");
            binding.passwordinput.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){

                    Toast.makeText(SignUpActivity.this, "Register is successful", Toast.LENGTH_SHORT).show();

                }
                else{

                    if (task.getException() instanceof FirebaseAuthUserCollisionException){

                        Toast.makeText(SignUpActivity.this, "User is Already Registered", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(SignUpActivity.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });

    }

    public void signin(View view) {

        switch (view.getId())
        {
            case R.id.signintextview:
                startActivity(new Intent(SignUpActivity.this,AuthinticateActivity.class));
                break;
        }
    }
}
