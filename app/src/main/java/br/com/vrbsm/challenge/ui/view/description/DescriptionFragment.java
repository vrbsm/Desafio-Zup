package br.com.vrbsm.challenge.ui.view.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import br.com.vrbsm.challenge.ui.view.home.HomeFragment;
import br.com.vrbsm.challenge.ui.view.search.SearchResultsFragment;
import br.com.vrbsm.challenge.util.glide.GlideApp;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionFragment extends AbstractFragment implements DescriptionContract.View {
    @BindView(R.id.img_description)
    ImageView imageDescription;
    @BindView(R.id.plot)
    TextView txDescription;
    @BindView(R.id.year)
    TextView txYear;
    @BindView(R.id.runtime)
    TextView txRuntime;
    @BindView(R.id.genre)
    TextView txGenre;
    @BindView(R.id.director)
    TextView txDirector;
    @BindView(R.id.writer)
    TextView txWriter;
    @BindView(R.id.actors)
    TextView txActors;
    @BindView(R.id.type)
    TextView txType;
    @BindView(R.id.country)
    TextView txCountry;
    @BindView(R.id.language)
    TextView txLanguage;
    @BindView(R.id.awards)
    TextView txAwards;
    @BindView(R.id.ratings)
    TextView txRatings;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private String mIdImdb;
    private DescriptionContract.Presenter mPresenter;

    private Movie mMovie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new DescriptionPresenterImpl(this);
        setHasOptionsMenu(true);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem delete = menu.findItem(R.id.delete);
        MenuItem save = menu.findItem(R.id.save);
        if(mMovie != null){
            List<Movie> movie = Movie.find(Movie.class, "imdbid = ?", new String[]{mMovie.getImdbid()}, null, null, "1");
            if (!movie.isEmpty()) {
                delete.setVisible(true);
                save.setVisible(false);
            }
            else{
                delete.setVisible(false);
                save.setVisible(true);
            }
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                if (mMovie != null) {
                    mMovie.save();
                    Snackbar.make(getView(), R.string.movie_added, Snackbar.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                }
                break;
            case R.id.delete:
                if(mMovie != null){
//                    Movie.find(Movie.class, "imdbid = ?", new String[]{mMovie.getImdbid()}, null, null, "1").get(0).delete();
                    mMovie.delete();
                    Snackbar.make(getView(), R.string.movie_deleted, Snackbar.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_menu_description, menu);
    }


    private void getBundle(Bundle bundle) {
        if (bundle != null) {
            if (bundle.containsKey(SearchResultsFragment.SEARCH_EXTRA)) {
                mIdImdb = bundle.getString(SearchResultsFragment.SEARCH_EXTRA);
                mPresenter.descriptionMovie(mIdImdb, (AbstractActivity) getActivity());

            } else if (bundle.containsKey(HomeFragment.MOVIE_ARGS)) {
                mMovie = getMovie(bundle);
                descriptionMovie(mMovie);
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container, false);
        ButterKnife.bind(this, view);
        toolbar.setTitleTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        ((AbstractActivity) getActivity()).setSupportActionBar(toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(getActivity().getResources().getColor(R.color.colorWhite));

        getBundle(getArguments());

        return view;
    }

    private Movie getMovie(Bundle bundle) {
        mIdImdb = (String) bundle.getSerializable(HomeFragment.MOVIE_ARGS);
        List<Movie> m = Movie.find(Movie.class, "imdbid = ?", new String[]{mIdImdb}, null, null, "1");

        return m.get(0);
    }

    @Override
    public void descriptionMovie(Movie movie) {
        mMovie = movie;
        getActivity().invalidateOptionsMenu();
        collapsingToolbarLayout.setTitle(movie.getTitle());
        txDescription.setText(getString(R.string.plot) + movie.getPlot());
        txDirector.setText(getString(R.string.director) + movie.getDirector());
        txGenre.setText(getString(R.string.genre) + movie.getGenre());
        txRuntime.setText(movie.getRuntime());
        txWriter.setText(getString(R.string.write) + movie.getWriter());
        txYear.setText(movie.getYear());
        txActors.setText(getString(R.string.actors) + movie.getActors());
        txAwards.setText(getString(R.string.awards) + movie.getAwards());
        txLanguage.setText(getString(R.string.language) + movie.getLanguage());
        txType.setText(movie.getType());
        txRatings.setText(movie.getRating());
        txCountry.setText(movie.getCountry());
        ((AbstractActivity) getActivity()).getSupportActionBar().setTitle(movie.getTitle());
        if (movie.getUrlImage() != null)
            if (!movie.getUrlImage().equals("N/A")) {
                GlideApp.with(getContext())
                        .load(movie.getUrlImage())
                        .placeholder(R.drawable.place_holder)
                        .error(R.drawable.place_holder)
                        .centerCrop()
                        .into(imageDescription);
            } else {
                imageDescription.setBackgroundResource(R.drawable.place_holder);
            }

    }

    @Override
    public void notFoundMovie() {
        Snackbar.make(getView(), R.string.movie_not_found, Snackbar.LENGTH_LONG).show();

    }
}
