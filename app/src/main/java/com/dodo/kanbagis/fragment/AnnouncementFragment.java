package com.dodo.kanbagis.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dodo.kanbagis.activity.LoginActivity;
import com.dodo.kanbagis.activity.MainActivity;
import com.dodo.kanbagis.activity.SplashActivity;
import com.dodo.kanbagis.adapter.BloodAdapter;
import com.dodo.kanbagis.databinding.FragmentAnnouncementBinding;
import com.dodo.kanbagis.module.Blood;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnnouncementFragment extends Fragment {

    private FragmentAnnouncementBinding binding;
    BloodAdapter bloodAdapter = new BloodAdapter();
    private ArrayList<Blood> bloodArrayList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.blondeList.setAdapter(bloodAdapter);
        pushAndRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        pushAndRefresh();
    }

    private void pushAndRefresh() {
        new Handler().postDelayed(() -> {

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Blood");
            bloodArrayList.clear();
            // Read from the database
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    for (DataSnapshot templateSnapshot : dataSnapshot.getChildren()) {
                        Blood blood = templateSnapshot.getValue(Blood.class);
                        if (blood != null) {
                            bloodArrayList.add(blood);
                        }
                    }
                    bloodAdapter.changeAll(bloodArrayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        },1000);
    }
}