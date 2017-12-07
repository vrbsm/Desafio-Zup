package br.com.vrbsm.challenge.ui.view.search;

import br.com.vrbsm.challenge.BuildConfig;
import br.com.vrbsm.challenge.api.MovieApi;
import br.com.vrbsm.challenge.model.Search;
import br.com.vrbsm.challenge.rest.RestGenerator;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vmascare on 04/12/17.
 */

public class SearchResultsInteractorImpl implements SearchResultsContract.Interactor {


    @Override
    public void searchMovie(String movie, final SearchResultsContract.CallBack callback, final OnDialogListener dialogListener) {
        dialogListener.showDialog();
        RestGenerator.createService(MovieApi.class).searchAllMovies(BuildConfig.API_KEY, movie).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                dialogListener.dismissDialog();

                if (response.isSuccessful()) {
                    if(response.body().getResponse().equals("True")) {
                        callback.moviesResult(response.body().getSearch());
                    }else{
                        callback.movieError(response.body().getError());
                    }
                } else {
                    callback.movieError("Houve um erro no servidor.");

                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                dialogListener.dismissDialog();
                t.printStackTrace();
                callback.movieError("Verifique sua internet e tente novamente em instantes");
            }
        });
    }
}
