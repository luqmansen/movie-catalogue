package luqmansen.me.moviecatalogue1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener
{
    public static final String EXTRA_MOVIE = "extra_movie";
    TextView titleObject;
    TextView descObject;
    TextView releaseObject;
    ImageView movieBgObject;
    ImageView playButton;
    TextView watchTrailer;
    ImageView trailerBg;
    String movieTrailerId;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        titleObject = findViewById(R.id.titleDetail);
        descObject = findViewById(R.id.descDetail);
        releaseObject = findViewById(R.id.releaseDetail);
        movieBgObject = findViewById( R.id.movieImageDetail );
        trailerBg = findViewById(R.id.trailerView);
        playButton = findViewById(R.id.playButton);
        watchTrailer = findViewById(R.id.watchTrailer);

        //Collect the intent
        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra(EXTRA_MOVIE);

        //Collect all movie value
        String title = movie.getTitle();
        String release = movie.getRelease();
        String desc = movie.getDesc();
        Integer image = movie.getMovieBg();
        movieTrailerId = movie.getMovieTrailerId();

        titleObject.setText(title);
        releaseObject.setText(release);
        descObject.setText(desc);
        descObject.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        movieBgObject.setImageResource( image );
        trailerBg.setImageResource(image);

        //For play trailer icon or text click listener
        playButton.setOnClickListener(this);
        watchTrailer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + movieTrailerId ));
        startActivity(intent);
    }
}
