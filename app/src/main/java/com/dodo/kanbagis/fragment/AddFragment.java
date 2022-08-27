package com.dodo.kanbagis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.FragmentAddBinding;
import com.dodo.kanbagis.module.Blood;
import com.dodo.kanbagis.utils.Validator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(view1 -> {
            // Write a message to the database
            DatabaseReference  mDatabase = FirebaseDatabase.getInstance().getReference();

            String adress = binding.bloodAdressText.getText().toString();
            String bloodGroup = binding.bloodGroupText.getText().toString();
            String messages = binding.bloodMessageText.getText().toString();
            String phone = binding.bloodPhoneText.getText().toString();
            String rh = binding.bloodRhText.getText().toString();


            if (isFormValid()) {

                Blood blood = new Blood(adress,bloodGroup,messages,phone,rh);
                mDatabase.child("Blood").child(Objects.requireNonNull(mDatabase.push().getKey())).setValue(blood);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isFormValid() {
        return Validator.getInstance()
                .isNotEmpty(binding.bloodAdressText, getResources().getString(R.string.Please_enter_your_address))
                .isNotEmpty(binding.bloodGroupText, getResources().getString(R.string.Please_enter_your_blood_group))
                .isBlood(binding.bloodGroupText, getResources().getString(R.string.Please_just_blood_group))
                .isNotEmpty(binding.bloodMessageText, getResources().getString(R.string.Please_enter_your_message))
                .isNotEmpty(binding.bloodPhoneText, getResources().getString(R.string.Please_enter_your_phone))
                .isPhoneNumberCharacter(binding.bloodPhoneText, getResources().getString(R.string.Please_control_your_phone_number))
                .isNotEmpty(binding.bloodRhText, getResources().getString(R.string.Please_enter_your_rh))
                .isRh(binding.bloodRhText, getResources().getString(R.string.Please_just_rh))
                .isValid();
    }
}