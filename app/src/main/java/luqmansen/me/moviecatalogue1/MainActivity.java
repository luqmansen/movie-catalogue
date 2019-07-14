package luqmansen.me.moviecatalogue1;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    final Fragment fragmentMovie = new movieFragment();
    final Fragment fragmentTVshows = new tvshowsFrament();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentMovie;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_movie:
                    fm.beginTransaction().hide(active).show(fragmentMovie).commit();
                    active = fragmentTVshows;
                    return true;
                case R.id.navigation_tvshows:
                    fm.beginTransaction().hide(active).show(fragmentTVshows).commit();
                    active = fragmentTVshows;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.container_layout, fragmentTVshows,"TVShows").hide(fragmentTVshows).commit();
        fm.beginTransaction().add(R.id.container_layout, fragmentMovie, "Movie").commit();
    }


}
