package com.dodo.kanbagis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dodo.kanbagis.API.ApiClient;
import com.dodo.kanbagis.API.ServiceAPI;
import com.dodo.kanbagis.API.response.User;
import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.ActivityLoginBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    private void logIn() {
        String email = binding.loginLayout.editTextEmail.getText().toString();
        String password = binding.loginLayout.editTextPassword.getText().toString();
        if (email.isEmpty() && password.isEmpty())
            return;

        binding.progressBar.setVisibility(View.VISIBLE);

        ServiceAPI serviceAPI = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<List<User>> call = serviceAPI.getUserByMailAndPassword(email,password);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    showLoginFailed();
                    binding.progressBar.setVisibility(View.GONE);
                    return;
                }

                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
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

        User user = new User(email,password,name,"izgi",number);
        ServiceAPI serviceAPI = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<User> call = serviceAPI.postUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful())
                    return;

                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showLoginFailed();
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showLoginFailed() {
        Toast.makeText(getApplicationContext(), R.string.control_error, Toast.LENGTH_LONG).show();
    }
}