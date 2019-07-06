package luqmansen.me.moviecatalogue1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] dataTitle;
    private String[] dataRelease;
    private String[] dataDescription;
    private TypedArray dataBg;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );



        adapter = new MovieAdapter( this );

        ListView listView = findViewById( R.id.listviewID );
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
            movie.setMovieBg( dataBg.getResourceId( i, -1 ) );
            movies.add( movie );
        }
        adapter.setMovies( movies );

    }

    private void prepare(){
        dataTitle = getResources().getStringArray( R.array.movie_title );
        dataRelease = getResources().getStringArray( R.array.movie_release );
        dataBg = getResources().obtainTypedArray( R.array.movie_bg );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.homeDetailButton:

        }
    }
}
