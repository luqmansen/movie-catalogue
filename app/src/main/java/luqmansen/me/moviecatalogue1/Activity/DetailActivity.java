package luqmansen.me.moviecatalogue1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import luqmansen.me.moviecatalogue1.DB.DBHandler;
import luqmansen.me.moviecatalogue1.Model.Popular.Data;
import luqmansen.me.moviecatalogue1.Model.TrailerVideo.TrailerIdFetcher;
import luqmansen.me.moviecatalogue1.Model.TrailerVideo.YoutubeTrailerFetcherCallback;
import luqmansen.me.moviecatalogue1.R;
import luqmansen.me.moviecatalogue1.Util.DateParser;
import luqmansen.me.moviecatalogue1.Util.NetworkUtil;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, YoutubeTrailerFetcherCallback {
    public static final String EXTRA_MOVIE = "extra_movie";
    private final String TAG = "DetailActivity";
    TextView titleObject;
    TextView descObject;
    TextView releaseObject;
    ImageView playButton;
    TextView watchTrailer;
    ImageView posterImg;
    ImageView backdropImg;
    ProgressBar progressBar;
    String youtubeVideoId;
    ImageButton addToFavorites;
    Button deleteFavorite;


    @SuppressLint("WrongViewCast")
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


        playButton.setOnClickListener(this);
        watchTrailer.setOnClickListener(this);


//        deleteFavorite = findViewById(R.id.remove_favorite);
//        deleteFavorite.setOnClickListener(deleteFavorite());


        //Collect the intent
        Intent intent = getIntent();
        Data data = intent.getParcelableExtra(EXTRA_MOVIE);

        //Collect all movie value
        final Integer DataID = data.getId();
        final String title;
        String release;
        String type;

        if (data.getTitle() == null){
            title = data.getName();
            release = data.getFirstAirDate();
            type = "TV";
        } else {
            title = data.getTitle();
            release = data.getReleaseDate();
            type = "MOVIE";
        }

        final String type1 = type;

        final DateParser dateParser = new DateParser();
        release = dateParser.parseDateToddMMyyyy(release);
        final String date = release;

        TrailerIdFetcher trailerIdFetcher = new TrailerIdFetcher();
        trailerIdFetcher.getVideoId(DataID, type, this);


        final String desc = data.getOverview();
        final String poster = data.getPosterPath();
        final String backdrop = data.getBackdropPath();


        titleObject.setText(title);
        releaseObject.setText(release);
        descObject.setText(desc);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            descObject.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + poster)
                .into(posterImg);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + backdrop)
                .into(backdropImg);


        progressBar.setVisibility(View.GONE);

        addToFavorites = findViewById(R.id.favorite_button);
        addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(DetailActivity.this);
                db.insertFavorites(DataID.toString(),type1,title, date, desc, backdrop, poster, youtubeVideoId);
                Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
        });


    }



    public  void DeleteButton(){
        deleteFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.playButton | R.id.watchTrailer:

                NetworkUtil inteCheck = new NetworkUtil(this);
                if (inteCheck.isNetworkAvailable()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + youtubeVideoId));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Connect to Internet to Watch Trailer", Toast.LENGTH_SHORT).show();
                }

            case R.id.remove_favorite:
                Toast.makeText(getApplicationContext(), "COBA", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onSuccess(@NonNull String value) {
        youtubeVideoId = value;
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
//        Toast.makeText(getApplicationContext(), "Connection Error :" + throwable, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onError: " + throwable);
    }

    public void materialButtonLisener() {

    }



}
