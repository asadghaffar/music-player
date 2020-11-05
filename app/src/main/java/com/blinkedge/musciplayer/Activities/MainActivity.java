package com.blinkedge.musciplayer.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.TabViewAdapter.TabViewAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    // Custom Action Bar
    private ImageView menuImage;
    private PopupMenu menu;
    private TextView appName;
    private LinearLayout searchLinear;
    private ImageView backImageView;
    private ImageView searchIcon;
    private EditText searchSong;
    private ImageView filter;
    private MaterialAlertDialogBuilder materialAlertDialogBuilder;

    // Tab View
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);

        id();
        onClick();
        tabLayout();

    }

    private void tabLayout() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        TabViewAdapter tabViewAdapter = new TabViewAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(tabViewAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void id() {
        menuImage = findViewById(R.id.menu);
        appName = findViewById(R.id.appName);
        searchLinear = findViewById(R.id.searchLinear);
        backImageView = findViewById(R.id.backImageView);
        searchSong = findViewById(R.id.searchSong);
        searchIcon = findViewById(R.id.searchIcon);
        filter = findViewById(R.id.filter);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPger);
    }

    private void onClick() {

        menuImage.setOnClickListener(v -> {
            menu = new PopupMenu(MainActivity.this, menuImage);
            //Inflating the Menu using xml file
            menu.getMenuInflater().inflate(R.menu.menu_main, menu.getMenu());

            menuOnclick();

            menu.show();
        });

        searchIcon.setOnClickListener(v -> {
            appName.setVisibility(View.GONE);
            searchLinear.setVisibility(View.VISIBLE);

        });

        backImageView.setOnClickListener(v -> {
            searchLinear.setVisibility(View.GONE);
            appName.setVisibility(View.VISIBLE);

        });

        filter.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_filter_selection, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

    }

    private void menuOnclick() {
        //registering menu with OnMenuItemClickListener
        menu.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();

            if (id == R.id.action_settings) {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.action_rate) {
                Toast.makeText(this, "Rated", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.action_about) {
                Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
            }

            return true;
        });
    }


}