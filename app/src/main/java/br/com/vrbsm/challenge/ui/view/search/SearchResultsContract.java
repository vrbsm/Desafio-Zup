package br.com.vrbsm.challenge.ui.view.search;

import android.app.Activity;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;


public interface SearchResultsContract {
    interface View {
        void onSearchMovie(String movie);

        void moviesResult(List<Movie> movieList);

        void moviesError(String error);

        void goToDescriptionActivity(String idImdbMovie, Activity activity);

    }

    interface Presenter {
        void searchMovie(String movie, OnDialogListener onDialogListener);

        void goToDescriptionActivity(String idImdbMovie, Activity activity);

    }

    interface Interactor {
        void searchMovie(String movie, SearchResultsContract.CallBack callBack, OnDialogListener dialogListener);

    }

    interface CallBack {
        void moviesResult(List<Movie> list);

        void movieError(String error);
    }
}
