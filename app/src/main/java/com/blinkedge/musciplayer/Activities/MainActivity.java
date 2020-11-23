package com.blinkedge.musciplayer.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.blinkedge.musciplayer.MusicFilesModal.MusicFilesModal;
import com.blinkedge.musciplayer.R;
import com.blinkedge.musciplayer.TabViewAdapter.TabViewAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String MY_SORT_PREF = "SortOrder";

    // Custom Action Bar
    private ImageView menuImage;
    private PopupMenu menu;
    private ImageView searchIcon;

    // Tab View
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static final int REQUEST_CODE = 1;
    public static List<MusicFilesModal> temporaryAudioFilesModal;
    public static List<MusicFilesModal> tempAudioList;
    public static List<MusicFilesModal> tempArtistList;
    public static List<MusicFilesModal> tempAlbumList;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id();
        onClick();
        tabLayout();
        checkPermission();
        activityToFragment();

    }

    private void activityToFragment() {
        try {
            String albumKey = getIntent().getStringExtra("albumDetail");
            if (albumKey.equals("albumDetail")){}

        } catch (Exception e){

        }
    }

    private void id() {
        menuImage = findViewById(R.id.menuImage);
        searchIcon = findViewById(R.id.searchIcon);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPger);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceType")
    private void onClick() {

        menuImage.setOnClickListener(v -> {
            menu = new PopupMenu(MainActivity.this, menuImage);
            //Inflating the Menu using xml file
            menu.getMenuInflater().inflate(R.menu.menu_main, menu.getMenu());
            menuOnclick();
            menu.show();
        });

        searchIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {

        new MaterialAlertDialogBuilder(MainActivity.this).setIcon(R.drawable.ic_exit)
                .setTitle("Are you sure to exit")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> MainActivity.this.finish())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
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
            temporaryAudioFilesModal = getAllAudioFromDevice(this);

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
                getAllAudioFromDevice(this);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }


    }

    public List<MusicFilesModal> getAllAudioFromDevice(final Context context) {

        SharedPreferences sharedPreferences = getSharedPreferences(MY_SORT_PREF, MODE_PRIVATE);
        String sortOrder = sharedPreferences.getString("sortingOrder", "sortAscending");


        tempAudioList = new ArrayList<>();
        tempArtistList = new ArrayList<>();
        tempAlbumList = new ArrayList<>();
        String order = null;
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        /*switch (sortOrder) {
            case "sortAscending":
                order = MediaStore.MediaColumns.TITLE + " ASC";
                break;
            case "sortDescending":
                order = MediaStore.MediaColumns.TITLE + " DESC";
                break;
        }*/

        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.ArtistColumns.ARTIST,
                MediaStore.Audio.Media.DURATION};

        Cursor c = context.getContentResolver().query(uri, projection, null, null, order);

        if (c != null) {
            while (c.moveToNext()) {

                String path = c.getString(0);
                String album = c.getString(1);
                String artist = c.getString(2);
                String duration = c.getString(3);

                String title = path.substring(path.lastIndexOf("/") + 1);

                MusicFilesModal audioModel = new MusicFilesModal(path, title, artist, album, duration);
                MusicFilesModal artistModel = new MusicFilesModal(path, title, artist, album, duration);
                MusicFilesModal albumModel = new MusicFilesModal(path, title, artist, album, duration);

                Log.d("artistName_", artist);
                Log.d("path_", path);
                Log.d("albumName_", album);

                tempAudioList.add(audioModel);
                tempArtistList.add(artistModel);
                tempAlbumList.add(albumModel);

            }
            c.close();
        }
        Log.d("tempAudioListSize_", tempAudioList.size() + "_");
        Log.d("tempArtistListSize_", tempArtistList.size() + "_");
        Log.d("tempAlbumListSize_", tempAlbumList.size() + "_");

        return tempAudioList;
    }

    @SuppressLint("CommitPrefEdits")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void menuOnclick() {
        //registering menu with OnMenuItemClickListener
        menu.setOnMenuItemClickListener(item -> {

            SharedPreferences.Editor sortingOrderSharedPreferences = getSharedPreferences(MY_SORT_PREF, MODE_PRIVATE).edit();

            int id = item.getItemId();

            if (id == R.id.action_settings) {
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
            } else if (id == R.id.sortAscending) {
                sortingOrderSharedPreferences.putString("sortingOrder", "sortAscending");
                sortingOrderSharedPreferences.apply();
                this.recreate();
                Toast.makeText(this, "list ascending", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.sortDescending) {
                sortingOrderSharedPreferences.putString("sortingOrder", "sortDescending");
                sortingOrderSharedPreferences.apply();
                this.recreate();
                Toast.makeText(this, "list desecing", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

}