package br.com.vrbsm.challenge.api;

import br.com.vrbsm.challenge.model.Search;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vrbsm on 03/12/17.
 */

public interface MovieApi {

    @GET(".")
    Call<Search> searchMovie(@Query("apikey") String apiKey, @Query("s") String search);
}
