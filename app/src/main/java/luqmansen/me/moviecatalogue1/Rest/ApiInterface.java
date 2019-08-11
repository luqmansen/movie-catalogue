package luqmansen.me.moviecatalogue1.Rest;

import luqmansen.me.moviecatalogue1.Model.Popular.DataResponse;
import luqmansen.me.moviecatalogue1.Model.TrailerVideo.DataVideoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

//    TODO add different language query

    @GET("movie/popular")
    Call<DataResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("tv/popular")
    Call<DataResponse> getPopularTV(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{id}/videos")
    Call<DataVideoResponse> getMovieVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/videos")
    Call<DataVideoResponse> getTvVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<DataResponse> getMovieDetails(@Path ("id") int id, @Query("api_key") String apiKey);
}
