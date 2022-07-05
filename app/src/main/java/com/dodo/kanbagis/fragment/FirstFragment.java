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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
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

            String adress = binding.blondeAdressText.getText().toString();
            String bloodGroup = binding.blondeGroupText.getText().toString();
            String messages = binding.blondeMessageText.getText().toString();
            String phone = binding.blondePhoneText.getText().toString();
            String rh = binding.blondeRhText.getText().toString();

            Blood blood = new Blood(adress,bloodGroup,messages,phone,rh);
            mDatabase.child("Blood").child(Objects.requireNonNull(mDatabase.push().getKey())).setValue(blood);

            if (trustValue()) {
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

    private boolean trustValue() {

        if (binding.blondeRhText.getText().toString() == null)

    }

}