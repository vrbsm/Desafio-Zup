package br.com.vrbsm.challenge.ui.view.home;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import java.util.List;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;
import br.com.vrbsm.challenge.ui.view.description.DescriptionContract;

public interface HomeContract {
    interface View {
        void goDescription(Movie movie);
        void goSearch();

    }

    interface Presenter {
        void goDescription(Fragment fragment, String args, int requestCode, Movie movie);
        void goSearch(Fragment fragment, int requestCode);
        List<Movie> movieSearchListDB();
    }

    interface Interactor {
        List<Movie> movieSearchListDB();
    }

}
