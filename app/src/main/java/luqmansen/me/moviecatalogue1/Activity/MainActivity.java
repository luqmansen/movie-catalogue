package luqmansen.me.moviecatalogue1.Activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.PersistableBundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import luqmansen.me.moviecatalogue1.Fragment.MovieFavoriteFragment;
import luqmansen.me.moviecatalogue1.Fragment.MovieFragment;
import luqmansen.me.moviecatalogue1.Fragment.TVFavoriteFragment;
import luqmansen.me.moviecatalogue1.Fragment.TvShowsFragment;
import luqmansen.me.moviecatalogue1.R;

import static luqmansen.me.moviecatalogue1.R.id.container_layout;


public class MainActivity extends AppCompatActivity {
    private final String TAG_MOVIE_FRAGEMENT = "TAG_MOVIE_FRAGMENT";
    private final String TAG_TV_FRAGMENT = "TAG_TV_FRAGMENT";
    private final String TAG_FAVORITES_FRAGMENT_TV = "TAG_FAVORITES_FRAGMENT_TV";
    private final String TAG_FAVORITES_FRAGMENT_MOVIE = "TAG_FAVORITES_FRAGMENT_MOVIE";
    public String title;

    Fragment fragmentMovie;
    Fragment fragmentTVshows;
    Fragment fragmentFavoritesTV;
    Fragment fragmentFavoritesMovie;
    Fragment active = fragmentMovie;
    final FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        hideSystemUI();

        if (savedInstanceState != null){
            fragmentMovie =  fm.findFragmentByTag(TAG_MOVIE_FRAGEMENT);
            fragmentTVshows = fm.findFragmentByTag(TAG_TV_FRAGMENT);
            fragmentFavoritesTV = fm.findFragmentByTag(TAG_FAVORITES_FRAGMENT_TV);
            fragmentFavoritesMovie = fm.findFragmentByTag(TAG_FAVORITES_FRAGMENT_MOVIE);
            active = fragmentMovie;

        }
        else {
            fragmentMovie = new MovieFragment();
            fragmentTVshows = new TvShowsFragment();
            fragmentFavoritesTV = new MovieFavoriteFragment();
            fragmentFavoritesMovie = new TVFavoriteFragment();
            active = fragmentMovie;
            fm.beginTransaction().add(container_layout, fragmentFavoritesTV, TAG_FAVORITES_FRAGMENT_TV).hide(fragmentFavoritesTV).commit();
            fm.beginTransaction().add(container_layout, fragmentFavoritesMovie, TAG_FAVORITES_FRAGMENT_MOVIE).hide(fragmentFavoritesMovie).commit();
            fm.beginTransaction().add(container_layout, fragmentTVshows, TAG_TV_FRAGMENT).hide(fragmentTVshows).commit();
            fm.beginTransaction().add(container_layout, fragmentMovie, TAG_MOVIE_FRAGEMENT).commit();
        }

        if (title == null) {
            title = getString(R.string.title_movie);
        }
        setActionBarTitle(title);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragmentMovie)
                            .commit();
                    title = getString(R.string.title_movie);
                    setActionBarTitle(title);
                    active = fragmentMovie;
                    return true;
                case R.id.navigation_tvshows:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragmentTVshows)
                            .commit();
                    title = getString(R.string.title_tv_shows);
                    setActionBarTitle(title);
                    active = fragmentTVshows;
                    return true;
                case R.id.navigation_favorites_movie:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragmentFavoritesMovie)
                            .commit();
                    title = getString(R.string.title_favorites_movie);
                    setActionBarTitle(title);
                    active = fragmentFavoritesMovie;
                    return true;
                case R.id.navigation_favorites_tv:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragmentFavoritesTV)
                            .commit();
                    title = getString(R.string.title_favorites_tv);
                    setActionBarTitle(title);
                    active = fragmentFavoritesTV;
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                fm.beginTransaction().remove(active).commit();
                startActivity(mIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void setActionBarTitle(String title) {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setTitle(title);
    }

}

