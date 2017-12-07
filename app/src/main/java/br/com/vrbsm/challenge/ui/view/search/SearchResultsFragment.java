package br.com.vrbsm.challenge.ui.view.search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.adapter.MovieListSearchResultAdapter;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by vmascare on 04/12/17.
 */

public class SearchResultsFragment extends AbstractFragment implements SearchResultsContract.View, SearchView.OnQueryTextListener {
    @BindView(R.id.movie_list_search)
    ListView listView;
    @BindView(R.id.empty_list)
    TextView emptyView;
    public static final String SEARCH_EXTRA = "SEARCH_EXTRA";
    private SearchResultsContract.Presenter mPresenter;
    private MovieListSearchResultAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new SearchResultsPresenterImpl(this);
        setHasOptionsMenu(true);
        listView.setEmptyView(emptyView);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_search, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);
        searchView.onActionViewExpanded();
        super.onCreateOptionsMenu(menu, inflater);
    }


    @OnItemClick(R.id.movie_list_search)
    public void onItemClickListener(int position) {
        if (mAdapter != null)
            goToDescriptionActivity(((Movie) mAdapter.getItem(position)).getImdbid(), getActivity());
    }

    @Override
    public void onSearchMovie(String movie) {
        mPresenter.searchMovie(movie, (AbstractActivity) getActivity());
    }

    @Override
    public void moviesResult(List<Movie> movieList) {
        mAdapter = new MovieListSearchResultAdapter(movieList);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void moviesError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_LONG).show();
        mAdapter = new MovieListSearchResultAdapter(new ArrayList<Movie>());
        listView.setAdapter(mAdapter);
    }

    @Override
    public void goToDescriptionActivity(String idImdbMovie, Activity activity) {
        mPresenter.goToDescriptionActivity(idImdbMovie,activity);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        onSearchMovie(query);
        hideKeyboard(getView());
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
