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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] dataTitle;
    private String[] dataRelease;
    private String[] dataDescription;
    private TypedArray dataBg;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    Context context;
    ListView listView;
    Button buttonHomeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView( R.layout.listview_row );
        buttonHomeDetail = findViewById( R.id.homeDetailButton );
        buttonHomeDetail.setClickable(true);
        buttonHomeDetail.setOnClickListener(this);

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        adapter = new MovieAdapter( this );
        final ListView listView = findViewById( R.id.listviewID );
        listView.setAdapter( adapter );

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, "An item clocked", Toast.LENGTH_SHORT).show();
            }
        });

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

    private void prepare(){
        dataTitle = getResources().getStringArray( R.array.movie_title );
        dataRelease = getResources().getStringArray( R.array.movie_release );
        dataDescription = getResources().getStringArray( R.array.movie_desc );
        dataBg = getResources().obtainTypedArray( R.array.movie_bg );
    }

    public void onClick(View view) {
                Movie movie = new Movie();

//                movie.setTitle( dataTitle[position] );
//                movie.setRelease( dataRelease[position] );
//                movie.setDesc( dataDescription[position] );
//                //noinspection ResourceType
//                movie.setMovieBg( dataBg.getResourceId( position, -1 ) );
//
//                Intent movieDetail = new Intent( MainActivity.this, MovieDetailActivity.class );
//                movieDetail.putExtra( EXTRA_MOVIE, movie );
//                startActivity( movieDetail );
//
    }


}
