package luqmansen.me.moviecatalogue1.Model.TrailerVideo;

import androidx.annotation.NonNull;

public interface YoutubeTrailerFetcherCallback {

        void onSuccess(@NonNull String value);

        void onError(@NonNull Throwable throwable);

}
