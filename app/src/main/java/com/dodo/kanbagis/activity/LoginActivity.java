package com.dodo.kanbagis.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dodo.kanbagis.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginLayout.signUpButton.setOnClickListener(v -> binding.vf.setDisplayedChild(1));
        binding.registerLayout.signInButton.setOnClickListener(v -> binding.vf.setDisplayedChild(0));

        binding.loginLayout.cirLoginButton.setOnClickListener(v -> logIn());
        binding.registerLayout.registerButton.setOnClickListener(v -> signUp());
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

        }
    }

    private void logIn() {

        String email = binding.loginLayout.editTextEmail.getText().toString();
        String password = binding.loginLayout.editTextPassword.getText().toString();
        if (email.isEmpty() && password.isEmpty())
            return;

        binding.progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent myIntent = new Intent(this, MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    } else {
                        showLoginFailed();
                        binding.progressBar.setVisibility(View.GONE);
                    }
                });

    }

    private void signUp() {

        String email = binding.registerLayout.registerEmail.getText().toString();
        String password = binding.registerLayout.registerPassword.getText().toString();
        String name = binding.registerLayout.registerName.getText().toString();
        String number = binding.registerLayout.registerNumber.getText().toString();

        if (email.isEmpty() && password.isEmpty() && name.isEmpty() && number.isEmpty())
            return;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();

                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showLoginFailed() {
        Toast.makeText(getApplicationContext(), "hata", Toast.LENGTH_SHORT).show();
    }
}