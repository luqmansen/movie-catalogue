package luqmansen.me.moviecatalogue1.App;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import luqmansen.me.moviecatalogue1.BuildConfig;
import luqmansen.me.moviecatalogue1.Model.Popular.Data;
import luqmansen.me.moviecatalogue1.Model.TrailerVideo.DataVideoResponse;
import luqmansen.me.moviecatalogue1.Model.TrailerVideo.DataVideos;
import luqmansen.me.moviecatalogue1.R;
import luqmansen.me.moviecatalogue1.Rest.ApiClient;
import luqmansen.me.moviecatalogue1.Rest.ApiInterface;
import luqmansen.me.moviecatalogue1.Util.DateParser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
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

        //Collect the intent
        Intent intent = getIntent();
        Data data = intent.getParcelableExtra(EXTRA_MOVIE);

        //Collect all movie value
        Integer DataID = data.getId();
        String title;
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
        getVideoId(DataID, type);

        DateParser dateParser = new DateParser();
        release = dateParser.parseDateToddMMyyyy(release);

        String desc = data.getOverview();
        String poster = data.getPosterPath();
        String backdrop = data.getBackdropPath();


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
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + youtubeVideoId));
        startActivity(intent);
    }

    public void getVideoId(final Integer id, String type) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        if (type == "TV") {
            Call<DataVideoResponse> call = apiService.getTvVideos(id, BuildConfig.API_KEY);
            call.enqueue(new Callback<DataVideoResponse>() {
                @Override
                public void onResponse(Call<DataVideoResponse> call, Response<DataVideoResponse> response) {
                    List<DataVideos> dataVideos = response.body().getResults();
                    Log.d("BISMILLAH", String.valueOf(dataVideos));
                    youtubeVideoId = dataVideos.get(0).getYoutubeID();
                }

                @Override
                public void onFailure(Call<DataVideoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "API Request Failed", Toast.LENGTH_LONG).show();
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            Call<DataVideoResponse> call = apiService.getMovieVideos(id, BuildConfig.API_KEY);
            call.enqueue(new Callback<DataVideoResponse>() {
                @Override
                public void onResponse(Call<DataVideoResponse> call, Response<DataVideoResponse> response) {
                    List<DataVideos> dataVideos = response.body().getResults();
                    Log.d("BISMILLAH", String.valueOf(dataVideos));
                    youtubeVideoId = dataVideos.get(0).getYoutubeID();
                }

                @Override
                public void onFailure(Call<DataVideoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "API Request Failed", Toast.LENGTH_LONG).show();
                    Log.e(TAG, t.toString());
                }
            });
        }
    }
}
