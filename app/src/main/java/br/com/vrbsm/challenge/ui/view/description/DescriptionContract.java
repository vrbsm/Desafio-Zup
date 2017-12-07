package br.com.vrbsm.challenge.ui.view.description;

import android.content.Context;
import android.widget.ImageView;

import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.listener.OnDialogListener;

public interface DescriptionContract {

    interface View {
        void descriptionMovie(Movie movie);

        void notFoundMovie(String error);
    }

    interface Presenter {
        void descriptionMovie(String idImdb, OnDialogListener dialog);
        Movie movieSearchDB(String idImdb);
        void movieDeleteDB(String idImdb);
        void movieSaveDB(Movie movie);
        void loadImg( String load, ImageView imageView, Context context);
    }

    interface Interactor {
        void descriptionMovie(String idImdb, OnDialogListener dialog, DescriptionContract.Callback callback);
        Movie movieSearchDB(String idImdb);
        void movieDeleteDB(String idImdb);
        void movieSaveDB(Movie movie);
    }

    interface Callback {
        void descriptionMovie(Movie movie);

        void notFoundMovie(String error);
    }
}
