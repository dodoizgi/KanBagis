package com.dodo.kanbagis.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.dodo.kanbagis.API.response.User;
import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.FragmentProfileBinding;
import com.dodo.kanbagis.utils.Prefs;
import com.dodo.kanbagis.utils.StringUtils;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        getUserInfo();
        createButtons();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getUserInfo() {
        ArrayList<User> users;
        Class<User> userClass = User.class;
        users = Prefs.getArrayList("user",userClass);
        if (users.size()<=0)
            return;

        User user = users.get(0);
        binding.accountUserName.setText(user.name);
        binding.letterIcon.setText(StringUtils.letterIconCreate(user.name));
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void createButtons() {
        binding.profileMyAccount.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_ProfileFragment_to_AccountFragment));
        binding.profileMyMesssages.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_ProfileFragment_to_MessageFragment));
        binding.profileMyBloodRequest.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_ProfileFragment_to_MyDonationFragment));
        binding.profileLogOut.setOnClickListener(v -> {
            Prefs.clearAll();
            Navigation.findNavController(v).navigate(R.id.action_ProfileFragment_to_LoginFragment);
        });
        binding.settingsHelp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + "05531108254"));
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });
    }
}