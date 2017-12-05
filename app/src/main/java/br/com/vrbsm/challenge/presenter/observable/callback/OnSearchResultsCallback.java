package br.com.vrbsm.challenge.presenter.observable.callback;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;

/**
 * Created by vmascare on 04/12/17.
 */

public interface OnSearchResultsCallback {

    void moviesResult(List<Movie> list);
    void movieError();
}
