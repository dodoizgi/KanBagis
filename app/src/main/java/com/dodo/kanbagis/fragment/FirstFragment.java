package com.dodo.kanbagis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.FragmentFirstBinding;
import com.dodo.kanbagis.module.Blood;
import com.dodo.kanbagis.utils.StringUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
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


            if (isFormValid(adress, bloodGroup, messages, phone, rh)) {

                Blood blood = new Blood(adress,bloodGroup,messages,phone,rh);
                mDatabase.child("Blood").child(Objects.requireNonNull(mDatabase.push().getKey())).setValue(blood);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean isFormValid(String adress, String bloodGroup, String messages, String phone, String rh) {

        if (StringUtils.isNullOrEmpty(adress))
            binding.bloodAdressText.setError("Please enter your address");

        if (StringUtils.isNullOrEmpty(bloodGroup))
            binding.bloodGroupText.setError("Please enter your blood group");

        if (StringUtils.isNullOrEmpty(messages))
            binding.bloodMessageText.setError("Please enter your message");

        if (StringUtils.isNullOrEmpty(phone))
            binding.bloodPhoneText.setError("Please enter your phone");

        if (StringUtils.isNullOrEmpty(rh))
            binding.bloodRhText.setError("Please enter your rh");


            return !StringUtils.isNullOrEmpty(adress) && !StringUtils.isNullOrEmpty(bloodGroup) &&
                    !StringUtils.isNullOrEmpty(messages) && !StringUtils.isNullOrEmpty(phone) &&
                    !StringUtils.isNullOrEmpty(rh);

    }

}