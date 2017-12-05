package br.com.vrbsm.challenge.api;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.model.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovieApi {

    @GET(".")
    Call<Search> searchAllMovies(@Query("apikey") String apiKey, @Query("s") String search);

    @GET(".")
    Call<Movie> searchMovieById(@Query("apikey") String apiKey, @Query("i") String imdbID);
}
