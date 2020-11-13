package com.blinkedge.musciplayer.TabViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.blinkedge.musciplayer.Fragments.AlbumsFragment;
import com.blinkedge.musciplayer.Fragments.ArtistFragment;
import com.blinkedge.musciplayer.Fragments.PlaylistsFragment;
import com.blinkedge.musciplayer.Fragments.TracksFragment;

public class TabViewAdapter extends FragmentStatePagerAdapter {

    public TabViewAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return new TracksFragment();
        else if (position == 1)
            return new ArtistFragment();
        else if (position == 2)
            return new AlbumsFragment();
        else if (position == 3)
            return new PlaylistsFragment();
        /*else if (position == 4)
            return new FavouriteFragment();*/

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){

        if (position == 0)
            return "Tracks";
        else if (position == 1)
            return "Artist";
        else if (position == 2)
            return "Albums";
        else if (position == 3)
            return "Playliste";
        /*else if (position == 4)
            return "Favorite";*/
        else
        return "";
    }

}
