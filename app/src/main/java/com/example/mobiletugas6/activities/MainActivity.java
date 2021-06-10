package com.example.mobiletugas6.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mobiletugas6.R;
import com.example.mobiletugas6.fragments.AiringFragment;
import com.example.mobiletugas6.fragments.NowPlayingFragment;
import com.example.mobiletugas6.fragments.PopularFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_item_now_playing);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.bottom_nav_item_now_playing:
                getSupportActionBar().setTitle("Now Playing");
                fragment = new NowPlayingFragment();
                break;
            case R.id.bottom_nav_item_popular:
                getSupportActionBar().setTitle("Popular");
                fragment = new PopularFragment();
                break;
            case R.id.bottom_nav_item_airing:
                getSupportActionBar().setTitle("Airing Today");
                fragment = new AiringFragment();
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}