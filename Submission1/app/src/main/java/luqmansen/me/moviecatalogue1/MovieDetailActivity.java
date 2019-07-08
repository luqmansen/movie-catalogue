package luqmansen.me.moviecatalogue1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    TextView titleObject;
    TextView descObject;
    TextView releaseObject;
    ImageView movieBgObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleObject = findViewById(R.id.titleDetail);
        descObject = findViewById(R.id.descDetail);
        releaseObject = findViewById(R.id.releaseDetail);
        movieBgObject = findViewById( R.id.movieImageDetail );

        //Collect the intent
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(EXTRA_MOVIE);

        //Collect all movie value
        String title = movie.getTitle();
        String release = movie.getRelease();
        String desc = movie.getDesc();
        Integer image = movie.getMovieBg();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        titleObject.setText(title);
        releaseObject.setText(release);
        descObject.setText(desc);
        movieBgObject.setImageResource( image );


    }
}
