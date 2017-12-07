package br.com.vrbsm.challenge.ui.view.search;

import android.app.Activity;
import android.content.Intent;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import br.com.vrbsm.challenge.ui.view.description.DescriptionActivity;

/**
 * Created by vmascare on 04/12/17.
 */

public class SearchResultsPresenterImpl implements SearchResultsContract.Presenter, SearchResultsContract.CallBack {
    private SearchResultsContract.View mView;
    private SearchResultsContract.Interactor mInteractor;

    public SearchResultsPresenterImpl(SearchResultsContract.View view) {
        this.mView = view;
        this.mInteractor = new SearchResultsInteractorImpl();
    }

    @Override
    public void moviesResult(List<Movie> list) {
        mView.moviesResult(list);
    }

    @Override
    public void movieError(String error) {
        mView.moviesError(error);
    }

    @Override
    public void searchMovie(String movie, OnDialogListener onDialogListener) {
        mInteractor.searchMovie(movie, this, onDialogListener);

    }

    @Override
    public void goToDescriptionActivity(String idImdbMovie, Activity activity) {
        Intent intent = new Intent(activity, DescriptionActivity.class);
        intent.putExtra(SearchResultsFragment.SEARCH_EXTRA, idImdbMovie);
        activity.startActivity(intent);
    }
}
