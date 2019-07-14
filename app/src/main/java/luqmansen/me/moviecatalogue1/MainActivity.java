package luqmansen.me.moviecatalogue1;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    final Fragment fragmentMovie = new movieFragment();
    final Fragment fragmentTVshows = new tvshowsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentMovie;


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.container_layout,fragmentTVshows).hide(fragmentTVshows).commit();
        fm.beginTransaction().add(R.id.container_layout, fragmentMovie).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_movie:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragmentMovie)
                            .commit();
                    active = fragmentMovie;
                    return true;
                case R.id.navigation_tvshows:
                    fm.beginTransaction()
                            .hide(active)
                            .show(fragmentTVshows)
                            .commit();
                    active = fragmentTVshows;
                    return true;
            }
            return false;
        }
    };
}
