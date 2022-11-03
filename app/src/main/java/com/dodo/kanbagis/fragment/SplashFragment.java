package com.dodo.kanbagis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.FragmentSplashBinding;
import com.dodo.kanbagis.utils.Prefs;

public class SplashFragment extends Fragment {

    private NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSplashBinding binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        checkLogin();
    }

    private void checkLogin() {
        boolean isLoggedin = Prefs.getBoolean("loggedin");
        if (isLoggedin) {
            navController.navigate(R.id.action_SplashFragment_to_AnnouncementFragment);
        }else {
            navController.navigate(R.id.action_SplashFragment_to_LoginFragment);
        }
    }
}