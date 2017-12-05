package br.com.vrbsm.challenge.presenter.observable.interactor;

import br.com.vrbsm.challenge.presenter.observable.callback.OnSearchResultsCallback;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import br.com.vrbsm.challenge.ui.listener.OnErrorListener;

/**
 * Created by vmascare on 04/12/17.
 */

public interface SearchResultsInteractor {
    void searchMovie(String movie, OnSearchResultsCallback callback, OnDialogListener dialogListener);
}
