package br.com.vrbsm.challenge.presenter.business;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.presenter.interactor.SearchResultsInteractorImpl;
import br.com.vrbsm.challenge.presenter.observable.callback.OnSearchResultsCallback;
import br.com.vrbsm.challenge.presenter.observable.interactor.SearchResultsInteractor;
import br.com.vrbsm.challenge.presenter.observable.presenter.SearchResultsPresenter;
import br.com.vrbsm.challenge.presenter.observable.view.OnSearchResultsView;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import br.com.vrbsm.challenge.ui.listener.OnErrorListener;

/**
 * Created by vmascare on 04/12/17.
 */

public class SearchResultsPresenterImpl implements SearchResultsPresenter, OnSearchResultsCallback {
    private OnSearchResultsView mView;
    private SearchResultsInteractor mInteractor;

    public SearchResultsPresenterImpl(OnSearchResultsView mView) {
        this.mView = mView;
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
