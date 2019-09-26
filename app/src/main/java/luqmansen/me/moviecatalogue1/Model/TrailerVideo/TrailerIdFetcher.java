package luqmansen.me.moviecatalogue1.Model.TrailerVideo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import luqmansen.me.moviecatalogue1.BuildConfig;
import luqmansen.me.moviecatalogue1.Model.Popular.Data;
import luqmansen.me.moviecatalogue1.Rest.ApiClient;
import luqmansen.me.moviecatalogue1.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerIdFetcher {

    private final String TAG = "TrailerFetcher";

    public void getVideoId(final Integer id, String type, @Nullable final YoutubeTrailerFetcherCallback callback) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        if (type == "TV") {
            Call<DataVideoResponse> call = apiService.getTvVideos(id, BuildConfig.API_KEY);
            call.enqueue(new Callback<DataVideoResponse>() {
                @Override
                public void onResponse(Call<DataVideoResponse> call, Response<DataVideoResponse> response) {
                    List<DataVideos> dataVideos = response.body().getResults();
                    Log.d(TAG, String.valueOf(dataVideos));
                    String youtubeID = dataVideos.get(0).getYoutubeID();

                    if (callback != null){
                        callback.onSuccess(youtubeID);
                    }

                }

                @Override
                public void onFailure(Call<DataVideoResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
        } else {
            Call<DataVideoResponse> call = apiService.getMovieVideos(id, BuildConfig.API_KEY);
            call.enqueue(new Callback<DataVideoResponse>() {
                @Override
                public void onResponse(Call<DataVideoResponse> call, Response<DataVideoResponse> response) {
                    List<DataVideos> dataVideos = response.body().getResults();
                    Log.d(TAG, String.valueOf(dataVideos));
                    String youtubeID = dataVideos.get(0).getYoutubeID();

                    if (callback != null){
                        callback.onSuccess(youtubeID);
                    }
                }

                @Override
                public void onFailure(Call<DataVideoResponse> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    if (callback != null){
                        callback.onError(t);
                    }
                }
            });
        }
    }
}
