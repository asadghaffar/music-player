package com.blinkedge.musciplayer.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.blinkedge.musciplayer.MusicFiles.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.TabViewAdapter.TabViewAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    // Tab View
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static final int REQUEST_CODE = 1;
    public static ArrayList<MusicFilesModal> temporaryAudioFilesModal;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id();
        onClick();
        tabLayout();
        checkPermission();

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

    private void tabLayout() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        TabViewAdapter tabViewAdapter = new TabViewAdapter(getSupportFragmentManager(), 0);
        viewPager.setAdapter(tabViewAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

        } else {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            temporaryAudioFilesModal = getAllAudio(this);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //do what ever
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                getAllAudio(this);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static ArrayList<MusicFilesModal> getAllAudio(Context context) {

        ArrayList<MusicFilesModal> musicFilesModalArrayList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA // for path getter setter

        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(0);
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String duration = cursor.getString(3);
                String path = cursor.getString(4);

                MusicFilesModal musicFilesModal = new MusicFilesModal(path, title, artist, album, duration);

                Log.d("path: " + path, "album: " + album);

                musicFilesModalArrayList.add(musicFilesModal);
            }
            cursor.close();
        }
        return musicFilesModalArrayList;

    }

}