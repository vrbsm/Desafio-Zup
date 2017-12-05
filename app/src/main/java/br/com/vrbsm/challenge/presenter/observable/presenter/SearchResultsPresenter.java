package br.com.vrbsm.challenge.presenter.observable.presenter;

import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import br.com.vrbsm.challenge.ui.listener.OnErrorListener;

/**
 * Created by vmascare on 04/12/17.
 */

public interface SearchResultsPresenter {

    void searchMovie(String movie, OnDialogListener onDialogListener);

}
