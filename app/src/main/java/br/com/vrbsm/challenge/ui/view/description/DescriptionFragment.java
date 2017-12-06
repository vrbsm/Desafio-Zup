package br.com.vrbsm.challenge.ui.view.description;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                Log.d(getClass().getCanonicalName(), "Save@");
                if(mMovie != null)
                    mMovie.save();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container, false);
        ButterKnife.bind(this, view);
        toolbar.setTitleTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        ((AbstractActivity)getActivity()).setSupportActionBar(toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getActivity().getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setExpandedTitleColor(getActivity().getResources().getColor(R.color.colorWhite));

        Bundle bundle = getArguments();
        if (bundle != null) {
            mIdImdb = bundle.getString(SearchResultsFragment.SEARCH_EXTRA);
            mPresenter.descriptionMovie(mIdImdb, (AbstractActivity) getActivity());
        }
        return view;
    }

    @Override
    public void descriptionMovie(Movie movie) {
        mMovie = movie;
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
        txRatings.setText(movie.getRatings());
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
