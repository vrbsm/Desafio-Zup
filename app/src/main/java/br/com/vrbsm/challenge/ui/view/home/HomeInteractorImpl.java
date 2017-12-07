package br.com.vrbsm.challenge.ui.view.home;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;

/**
 * Created by vrbsm on 07/12/17.
 */

public class HomeInteractorImpl implements HomeContract.Interactor {
    @Override
    public List<Movie> movieSearchListDB() {
        List<Movie> list = Movie.listAll(Movie.class);
        return list;
    }
}
