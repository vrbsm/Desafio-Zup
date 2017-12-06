package br.com.vrbsm.challenge.ui.view.description;

import br.com.vrbsm.challenge.BuildConfig;
import br.com.vrbsm.challenge.api.MovieApi;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.rest.RestGenerator;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vmascare on 05/12/17.
 */

public class DescriptionInteractorImpl implements DescriptionContract.Interactor {
    @Override
    public void descriptionMovie(String idImdb, final OnDialogListener dialog, final DescriptionContract.Callback callback) {
        dialog.showDialog();
        RestGenerator.createService(MovieApi.class).searchMovieById(BuildConfig.API_KEY, idImdb).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                dialog.dismissDialog();
                if(response.isSuccessful()){
                    if(response.body().getResponse().equals("True")) {
                        callback.descriptionMovie(response.body());
                    }else{
                        callback.notFoundMovie();
                    }
                }else{
                    callback.notFoundMovie();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                dialog.dismissDialog();
                callback.notFoundMovie();
            }
        });
    }
}
