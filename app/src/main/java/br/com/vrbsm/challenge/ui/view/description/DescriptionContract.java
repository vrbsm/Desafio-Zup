package br.com.vrbsm.challenge.ui.view.description;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;

public interface DescriptionContract {

    interface View {
        void descriptionMovie(Movie movie);

        void notFoundMovie();
    }

    interface Presenter {
        void descriptionMovie(String idImdb, OnDialogListener dialog);
    }

    interface Interactor {
        void descriptionMovie(String idImdb, OnDialogListener dialog, DescriptionContract.Callback callback);
    }

    interface Callback {
        void descriptionMovie(Movie movie);

        void notFoundMovie();
    }
}
