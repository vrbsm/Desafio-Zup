package br.com.vrbsm.challenge.ui.view.description;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import br.com.vrbsm.challenge.util.glide.GlideApp;

import static java.security.AccessController.getContext;

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
        this.mInteractor.descriptionMovie(idImdb, dialog, this);
    }

    @Override
    public Movie movieSearchDB(String idImdb) {
        return mInteractor.movieSearchDB(idImdb);
    }

    @Override
    public void movieDeleteDB(String idImdb) {
        mInteractor.movieDeleteDB(idImdb);
    }

    @Override
    public void movieSaveDB(Movie movie) {
        mInteractor.movieSaveDB(movie);
    }

    @Override
    public void loadImg(String load, ImageView imageView, Context context) {
        if (load != null)
            if (!load.equals("N/A")) {
                GlideApp.with(context)
                        .load(load)
                        .placeholder(R.drawable.place_holder)
                        .error(R.drawable.place_holder)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            } else {
                imageView.setBackgroundResource(R.drawable.place_holder);
            }
    }



    @Override
    public void descriptionMovie(Movie movie) {
        mView.descriptionMovie(movie);
    }

    @Override
    public void notFoundMovie(String error) {
        mView.notFoundMovie(error);
    }
}
