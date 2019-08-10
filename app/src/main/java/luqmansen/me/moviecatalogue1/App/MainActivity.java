package luqmansen.me.moviecatalogue1.App;


import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import luqmansen.me.moviecatalogue1.Fragment.MovieFragment;
import luqmansen.me.moviecatalogue1.Fragment.TvShowsFragment;
import luqmansen.me.moviecatalogue1.R;



public class MainActivity extends AppCompatActivity {
    final Fragment fragmentMovie = new MovieFragment(this);
    final Fragment fragmentTVshows = new TvShowsFragment(this);
    final FragmentManager fm = getSupportFragmentManager();
    public String title;

    Fragment active = fragmentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.container_layout, fragmentTVshows).hide(fragmentTVshows).commit();
        fm.beginTransaction().add(R.id.container_layout, fragmentMovie).commit();

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
            }
            return false;
        }
    };

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
//            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    public void setActionBarTitle(String title) {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setTitle(title);

    }
}
