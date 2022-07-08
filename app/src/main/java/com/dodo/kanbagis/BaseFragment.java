package com.dodo.kanbagis;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dodo.kanbagis.databinding.BottomBarBinding;
import com.dodo.kanbagis.fragment.FirstFragment;
import com.dodo.kanbagis.fragment.SecondFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public abstract class BaseFragment extends AppCompatActivity {

    BottomNavigationView bottomBar;
    protected BottomBarBinding bottomBarBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(0, 0);
    }

    protected void bindTo(@NonNull View root) {
        bottomBarBinding = BottomBarBinding.bind(root);
        initBottomMenu();
    }

    protected void hideBottomBar() {
        if (bottomBarBinding != null)
            bottomBarBinding.bottomNavigationView.setVisibility(GONE);
    }

    protected void showBottomBar() {
        if (bottomBarBinding != null)
            bottomBarBinding.bottomNavigationView.setVisibility(VISIBLE);
    }

    protected void onMenuReselected() {
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    protected void onTopBarBackPressed() {
        finish();
        overridePendingTransition(0, 0);
    }


    private void initBottomMenu() {
        if (bottomBarBinding != null) {

            bottomBarBinding.bottomNavigationView.setItemIconTintList(null);
            bottomBarBinding.bottomNavigationView.setOnItemReselectedListener(item -> onMenuReselected());
            bottomBarBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
                menuItemSelected(item);
                return true;
            });
        }
    }

    public void bottomBarSetup(int bottomItemView) {
        bottomBar = findViewById(R.id.bottomNavigationView);
        bottomBar.setItemIconTintList(null);
        bottomBar.setSelectedItemId(bottomItemView);
        bottomBar.setOnItemSelectedListener(item -> {
            menuItemSelected(item);
            return true;
        });
    }

    public void menuItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_home:
                startActivity(SecondFragment.class);
                break;
            case R.id.action_add:
                startActivity(FirstFragment.class);
                break;
        }
    }

}