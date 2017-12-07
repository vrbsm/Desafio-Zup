package br.com.vrbsm.challenge.ui.view.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.view.description.DescriptionActivity;
import br.com.vrbsm.challenge.ui.view.search.SearchResultsActivity;

/**
 * Created by vrbsm on 07/12/17.
 */

public class HomePresenterImpl implements HomeContract.Presenter {
    private HomeContract.Interactor mInteractor;
    public HomePresenterImpl() {
        mInteractor = new HomeInteractorImpl();
    }


    @Override
    public void goDescription(Fragment fragment, String args, int requestCode, Movie movie) {
        Intent intent = new Intent(fragment.getActivity(), DescriptionActivity.class);
        intent.putExtra(args, movie.getImdbid());
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    public void goSearch(Fragment fragment, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), SearchResultsActivity.class);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    public List<Movie> movieSearchListDB() {
        return mInteractor.movieSearchListDB();
    }
}
