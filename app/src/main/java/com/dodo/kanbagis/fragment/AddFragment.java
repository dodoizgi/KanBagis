package com.dodo.kanbagis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
    private static final String[] BloodGroup = new String[]{
            "A", "B", "AB", "0"
    };
    private static final String[] BloodRh = new String[]{
            "+", "-"
    };

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

        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(requireContext(), R.layout.blonde_list_item, BloodGroup);
        ArrayAdapter<String> bloodRhAdapter = new ArrayAdapter<String>(requireContext(), R.layout.blonde_list_item, BloodRh);

        binding.addBloodGroup.setOnClickListener(view1 ->
            binding.addBloodGroup.showDropDown()
        );

        binding.addBloodRh.setOnClickListener(view1 ->
            binding.addBloodRh.showDropDown()
        );

        binding.addBloodGroup.setAdapter(bloodGroupAdapter);
        binding.addBloodRh.setAdapter(bloodRhAdapter);
        binding.approveButton.setOnClickListener(view1 -> {
            // Write a message to the database
            pushData();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void pushData() {
        // Write a message to the database
        DatabaseReference  mDatabase = FirebaseDatabase.getInstance().getReference();

        String adress = Objects.requireNonNull(binding.addAddress.getText()).toString();
        String bloodGroup = binding.addBloodGroup.getText().toString();
        String messages = Objects.requireNonNull(binding.addMessage.getText()).toString();
        String phone = Objects.requireNonNull(binding.addPhone.getText()).toString();
        String rh = binding.addBloodRh.getText().toString();

        if (isFormValid()) {
            Blood blood = new Blood(adress,bloodGroup,messages,phone,rh);
            mDatabase.child("Blood").child(Objects.requireNonNull(mDatabase.push().getKey())).setValue(blood);
            binding.addContainerLayout.setVisibility(View.GONE);
            binding.addSuccessContainerLayout.setVisibility(View.VISIBLE);
        }

    }

    private boolean isFormValid() {
        return Validator.getInstance()
                .isNotEmpty(binding.addAddress, getResources().getString(R.string.Please_enter_your_address))
                .isNotEmpty(binding.addBloodGroup, getResources().getString(R.string.Please_enter_your_blood_group))
                .isBlood(binding.addBloodGroup, getResources().getString(R.string.Please_just_blood_group))
                .isNotEmpty(binding.addMessage, getResources().getString(R.string.Please_enter_your_message))
                .isNotEmpty(binding.addPhone, getResources().getString(R.string.Please_enter_your_phone))
                .isPhoneNumberCharacter(binding.addPhone, getResources().getString(R.string.Please_control_your_phone_number))
                .isNotEmpty(binding.addBloodRh, getResources().getString(R.string.Please_enter_your_rh))
                .isRh(binding.addBloodRh, getResources().getString(R.string.Please_just_rh))
                .isValid();
    }
}