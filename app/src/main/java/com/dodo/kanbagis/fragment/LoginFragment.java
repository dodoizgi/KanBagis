package com.dodo.kanbagis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dodo.kanbagis.API.ApiClient;
import com.dodo.kanbagis.API.ServiceAPI;
import com.dodo.kanbagis.API.response.User;
import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.FragmentLoginBinding;
import com.dodo.kanbagis.utils.Prefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        binding.loginLayout.signUpButton.setOnClickListener(v -> binding.vf.setDisplayedChild(1));
        binding.registerLayout.signInButton.setOnClickListener(v -> binding.vf.setDisplayedChild(0));

        binding.loginLayout.applyButton.setOnClickListener(v -> logIn());
        binding.registerLayout.registerButton.setOnClickListener(v -> signUp());
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


                Prefs.put("loggedin", true);
                Prefs.put("user",response.body());
                navController.navigate(R.id.action_LoginFragment_to_AnnouncementFragment);

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

                navController.navigate(R.id.action_LoginFragment_to_AnnouncementFragment);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showLoginFailed();
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showLoginFailed() {
        Toast.makeText(getContext(), R.string.control_error, Toast.LENGTH_LONG).show();
    }
}