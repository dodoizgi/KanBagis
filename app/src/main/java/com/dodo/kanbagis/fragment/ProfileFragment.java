package com.dodo.kanbagis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        createButtons();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createButtons() {
        binding.profileMyAccount.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_AccountFragment));
        binding.profileMyMesssages.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_MessageFragment));
        binding.profileMyBloodRequest.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_MyDonationFragment));
        binding.profileMySettings.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_SettingsFragment));
    }
}