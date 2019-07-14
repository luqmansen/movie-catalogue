package luqmansen.me.moviecatalogue1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity
{
    public String[] dataTitle;
    public String[] dataRelease;
    public String[] dataDescription;
    public TypedArray dataBg;
    public String[] dataMovieTrailerId;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2);

        adapter = new MovieAdapter( this );
        final ListView listView = findViewById( R.id.listviewID );
        listView.setAdapter( adapter );

        prepare();
        addItem();
    }



    private void addItem()
    {
        movies = new ArrayList<>(  );

        for (int i = 0; i < dataTitle.length; i++)
        {
            Movie movie = new Movie(  );
            movie.setTitle( dataTitle[i] );
            movie.setRelease( dataRelease[i] );
            movie.setDesc( dataDescription[i] );
            movie.setMovieBg( dataBg.getResourceId( i, -1 ) );
            movie.setMovieTrailerId( dataMovieTrailerId[i] );
            movies.add( movie );
        }
        adapter.setMovies( movies );
    }

    public void prepare()
    {
        dataTitle = getResources().getStringArray( R.array.movie_title );
        dataRelease = getResources().getStringArray( R.array.movie_release );
        dataDescription = getResources().getStringArray( R.array.movie_desc );
        dataBg = getResources().obtainTypedArray( R.array.movie_bg );
        dataMovieTrailerId = getResources().getStringArray(R.array.movie_youtube_id);
    }
}
