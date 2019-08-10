package luqmansen.me.moviecatalogue1.App;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

import luqmansen.me.moviecatalogue1.Model.Data;
import luqmansen.me.moviecatalogue1.R;
import luqmansen.me.moviecatalogue1.Util.DateParser;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    TextView titleObject;
    TextView descObject;
    TextView releaseObject;
    ImageView playButton;
    TextView watchTrailer;
    ImageView posterImg;
    ImageView backdropImg;
    String movieTrailerId;

    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.progressBarDetail);
        progressBar.setVisibility(View.VISIBLE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        titleObject = findViewById(R.id.titleDetail);
        descObject = findViewById(R.id.descDetail);
        releaseObject = findViewById(R.id.releaseDetail);
        posterImg = findViewById(R.id.posterImageDetail);
        backdropImg = findViewById(R.id.backdropImageDetail);
        playButton = findViewById(R.id.playButton);
        watchTrailer = findViewById(R.id.watchTrailer); // ini text view

        //Collect the intent
        Intent intent = getIntent();
        Data data = intent.getParcelableExtra(EXTRA_MOVIE);

        //Collect all movie value
        String title;
        String release;

        if (data.getTitle() == null){
            title = data.getName();
            release = data.getFirstAirDate();
        } else {
            title = data.getTitle();
            release = data.getReleaseDate();
        }

        DateParser dateParser = new DateParser();
        release = dateParser.parseDateToddMMyyyy(release);


        String desc = data.getOverview();
        String poster = data.getPosterPath();
        String backdrop = data.getBackdropPath();
        movieTrailerId = "SUXWAEX2jlg";

        titleObject.setText(title);
        releaseObject.setText(release);
        descObject.setText(desc);
        descObject.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + poster)
                .into(posterImg);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + backdrop)
                .into(backdropImg);

        progressBar.setVisibility(View.GONE);


        //For play trailer icon or text click listener
        playButton.setOnClickListener(this);
        watchTrailer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + movieTrailerId));
        startActivity(intent);
    }
}
