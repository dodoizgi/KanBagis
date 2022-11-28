package com.dodo.kanbagis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dodo.kanbagis.API.ApiClient;
import com.dodo.kanbagis.API.ServiceAPI;
import com.dodo.kanbagis.API.response.User;
import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.FragmentAccountBinding;
import com.dodo.kanbagis.utils.KeyboardUtils;
import com.dodo.kanbagis.utils.Prefs;
import com.dodo.kanbagis.utils.StringUtils;
import com.dodo.kanbagis.utils.Validator;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private User user;
    private ArrayList<User> users;
    private final Class<User> userClass = User.class;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        KeyboardUtils.hideWhenTap(binding.accountScrool);
        /*binding.accountScrool.post(() -> {
            binding.accountScrool.smoothScrollTo(0, binding.getRoot().getTop());
        });*/
        createFragmentAccount();
        return binding.getRoot();
    }

    private void createFragmentAccount() {
        users = Prefs.getArrayList("user",userClass);

        if (users.size() == 0)
            return;

        user = users.get(0);

        binding.accountUserName.setText(user.name);
        binding.lastNameEditText.setText(user.lastname);
        binding.nameEditText.setText(user.name);
        binding.accountUserImage.setText(StringUtils.letterIconCreate(user.name));
        binding.emailEditText.setText(user.mail);
        binding.phoneEditText.setText(user.phone);
        binding.passwordEditText.setText(user.password);

        binding.applyButton.setOnClickListener(view -> {
            if (isFormValid()) {
                KeyboardUtils.forceCloseKeyboard(view);
                updatePost();
            }
        });
    }

    private void putPrefs() {
        user.setName(Objects.requireNonNull(binding.nameEditText.getText()).toString());
        user.setLastname(Objects.requireNonNull(binding.lastNameEditText.getText()).toString());
        user.setMail(Objects.requireNonNull(binding.emailEditText.getText()).toString());
        user.setPassword(Objects.requireNonNull(binding.passwordEditText.getText()).toString());
        Prefs.put("user",users);
    }

    private void updatePost() {

        ServiceAPI serviceAPI = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<User> call = serviceAPI.putUser(user.id, user.name, user.lastname,user.mail,user.password,user.phone);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.printf("Code: %d%n", response.code());
                    return;
                }
                putPrefs();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private boolean isFormValid() {
        return Validator.getInstance()
                .isNotEmpty(binding.nameEditText,getResources().getString(R.string.Please_enter_your_name))
                .isNotEmpty(binding.nameEditText,getResources().getString(R.string.Please_enter_your_last_name))
                .isNotEmpty(binding.emailEditText, getResources().getString(R.string.Please_enter_your_mail))
                .isNotEmpty(binding.passwordEditText, getResources().getString(R.string.Please_enter_your_password))
                .isNotEmpty(binding.phoneEditText, getResources().getString(R.string.Please_enter_your_phone))
                .isGreaterThanSix(binding.passwordEditText,getResources().getString(R.string.Please_control_your_password))
                .isEmail(binding.emailEditTextInput,getResources().getString(R.string.Please_enter_your_mail))
                .isPhoneNumberCharacterInput(binding.phoneEditTextInput, getResources().getString(R.string.Please_control_your_phone_number))
                .isValid();
    }
}