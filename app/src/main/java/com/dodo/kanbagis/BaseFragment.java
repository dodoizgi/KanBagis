package com.dodo.kanbagis;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dodo.kanbagis.databinding.BottomBarBinding;
import com.dodo.kanbagis.databinding.TopBarBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileDescriptor;
import java.io.PrintWriter;


public abstract class BaseFragment extends Fragment {

    BottomNavigationView bottomBar;
    protected TopBarBinding topBarBinding;
    protected BottomBarBinding bottomBarBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {
        super.dump(prefix, fd, writer, args);
    }



    protected void bindTo(@NonNull View root) {
        bottomBarBinding = BottomBarBinding.bind(root);
        bindTopBar(root);
    }

    protected void bindTopBar(@NonNull View root) {
        topBarBinding = TopBarBinding.bind(root);
        initTopBar();
    }

    private void initTopBar() {
        if (topBarBinding != null) {
            topBarBinding.topBarBack.setOnClickListener(view -> onStop());
        }
    }

    private void showTopBarBack() {
        if (topBarBinding != null) {
            topBarBinding.topBarBack.setVisibility(VISIBLE);
        }
    }

    protected void hideBottomBar() {
        if (bottomBarBinding != null)
            bottomBarBinding.bottomNavigationView.setVisibility(GONE);
    }

    protected void showBottomBar() {
        if (bottomBarBinding != null)
            bottomBarBinding.bottomNavigationView.setVisibility(VISIBLE);
    }
}