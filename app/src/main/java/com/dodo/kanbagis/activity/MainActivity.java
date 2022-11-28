package com.dodo.kanbagis.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dodo.kanbagis.R;
import com.dodo.kanbagis.databinding.ActivityMainBinding;
import com.dodo.kanbagis.utils.Prefs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Prefs.init(getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        NavController navController = Navigation.findNavController(this, R.id.container_fragment);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            binding.getRoot();
            int heightDiff = binding.getRoot().getRootView().getHeight() - binding.getRoot().getHeight();
            if (heightDiff > 200) {
                //keyboard is open, hide layout
                binding.bottomNavigationView.setVisibility(View.GONE);
            } else {
                //keyboard is hidden, show layout
                binding.bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });

        navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
            if(navDestination.getId() == R.id.LoginFragment || navDestination.getId() == R.id.SplashFragment) {
                bottomNavigationView.setVisibility(View.GONE);
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void bottomBarGone() {
            bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.SplashFragment) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}