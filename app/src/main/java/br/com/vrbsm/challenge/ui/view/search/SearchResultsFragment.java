package br.com.vrbsm.challenge.ui.view.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.presenter.business.SearchResultsPresenterImpl;
import br.com.vrbsm.challenge.presenter.observable.presenter.SearchResultsPresenter;
import br.com.vrbsm.challenge.presenter.observable.view.OnSearchResultsView;
import br.com.vrbsm.challenge.ui.adapter.MovieListAdapter;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import br.com.vrbsm.challenge.ui.view.description.DescriptionActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class SearchResultsFragment extends AbstractFragment implements OnSearchResultsView, SearchView.OnQueryTextListener {
    @BindView(R.id.movie_list_search)
    ListView listView;
    @BindView(R.id.empty_list)
    TextView emptyView;
    public static final String SEARCH_EXTRA = "SEARCH_EXTRA";
    private SearchResultsPresenter mPresenter;
    private MovieListAdapter mAdapter;

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

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            onSearchMovie(query);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.options_menu_search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);
    }

    private void goDescription(String idImdbMovie) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra(SEARCH_EXTRA, idImdbMovie);
        startActivity(intent);
    }

    @OnItemClick(R.id.movie_list_search)
    public void onItemClickListener(int position) {
        if (mAdapter != null)
            goDescription(((Movie) mAdapter.getItem(position)).getId());
    }

    @Override
    public void onSearchMovie(String movie) {
        mPresenter.searchMovie(movie, (AbstractActivity) getActivity());
    }

    @Override
    public void moviesResult(List<Movie> movieList) {
        mAdapter = new MovieListAdapter(movieList);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void moviesError() {
        Toast.makeText(getContext(), "Filme não encontrado", Toast.LENGTH_SHORT).show();
        mAdapter = new MovieListAdapter(new ArrayList<Movie>());
        listView.setAdapter(mAdapter);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        onSearchMovie(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
