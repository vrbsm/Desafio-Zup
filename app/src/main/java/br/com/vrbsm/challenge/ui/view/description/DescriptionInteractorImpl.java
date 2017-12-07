package br.com.vrbsm.challenge.ui.view.description;

import java.util.List;

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
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("True")) {
                        callback.descriptionMovie(response.body());
                    } else {
                        callback.notFoundMovie("Filme não encontrado");
                    }
                } else {
                    callback.notFoundMovie("Filme não encontrado");
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
                dialog.dismissDialog();
                callback.notFoundMovie("Verifique sua internet e tente novamente em instantes");
            }
        });
    }

    @Override
    public Movie movieSearchDB(String idImdb) {
        List<Movie> m = Movie.find(Movie.class, "imdbid = ?", new String[]{idImdb}, null, null, "1");
        return !m.isEmpty() ? m.get(0) : null ;
    }

    @Override
    public void movieDeleteDB(String idImdb) {
        List<Movie> movie = Movie.find(Movie.class, "imdbid = ?", new String[]{idImdb}, null, null, "1");
        if(!movie.isEmpty())
            movie.get(0).delete();
    }

    @Override
    public void movieSaveDB(Movie movie) {
        movie.save();
    }
}
