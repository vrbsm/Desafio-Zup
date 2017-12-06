package br.com.vrbsm.challenge.ui.view.description;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;

/**
 * Created by vmascare on 05/12/17.
 */

public class DescriptionPresenterImpl implements DescriptionContract.Presenter, DescriptionContract.Callback {

    private DescriptionContract.Interactor mInteractor;
    private DescriptionContract.View mView;

    public DescriptionPresenterImpl(DescriptionContract.View mView) {
        this.mView = mView;
        this.mInteractor = new DescriptionInteractorImpl();
    }

    @Override
    public void descriptionMovie(String idImdb, OnDialogListener dialog) {
        this.mInteractor.descriptionMovie(idImdb,dialog, this);
    }

    @Override
    public void descriptionMovie(Movie movie) {
        mView.descriptionMovie(movie);
    }

    @Override
    public void notFoundMovie() {
        mView.notFoundMovie();
    }
}
