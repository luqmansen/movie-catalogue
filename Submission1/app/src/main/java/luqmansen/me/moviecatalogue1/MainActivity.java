package luqmansen.me.moviecatalogue1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static luqmansen.me.moviecatalogue1.MovieDetailActivity.EXTRA_MOVIE;

public class MainActivity extends AppCompatActivity {

    public String[] dataTitle;
    public String[] dataRelease;
    public String[] dataDescription;
    public TypedArray dataBg;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        adapter = new MovieAdapter( this );
        final ListView listView = findViewById( R.id.listviewID );
        listView.setAdapter( adapter );

        prepare();
        addItem();
    }



    private void addItem(){
        movies = new ArrayList<>(  );

        for (int i = 0; i < dataTitle.length; i++){
            Movie movie = new Movie(  );
            movie.setTitle( dataTitle[i] );
            movie.setRelease( dataRelease[i] );
            movie.setDesc( dataDescription[i] );
            movie.setMovieBg( dataBg.getResourceId( i, -1 ) );
            movies.add( movie );
        }
        adapter.setMovies( movies );
    }

    public void prepare(){
        dataTitle = getResources().getStringArray( R.array.movie_title );
        dataRelease = getResources().getStringArray( R.array.movie_release );
        dataDescription = getResources().getStringArray( R.array.movie_desc );
        dataBg = getResources().obtainTypedArray( R.array.movie_bg );
    }



}
