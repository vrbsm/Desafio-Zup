package br.com.vrbsm.challenge.ui.view.search;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;

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
    public void movieError() {
        mView.moviesError();
    }

    @Override
    public void searchMovie(String movie, OnDialogListener onDialogListener) {
        mInteractor.searchMovie(movie, this, onDialogListener);

    }
}
