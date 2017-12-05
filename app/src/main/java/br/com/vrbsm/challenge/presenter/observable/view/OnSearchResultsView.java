package br.com.vrbsm.challenge.presenter.observable.view;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;

/**
 * Created by vmascare on 04/12/17.
 */

public interface OnSearchResultsView {

    void onSearchMovie(String movie);
    void moviesResult(List<Movie> movieList );
    void moviesError();


}
