package br.com.vrbsm.challenge.ui.view.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.adapter.MovieViewPagerAdapter;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import br.com.vrbsm.challenge.ui.view.description.DescriptionActivity;
import br.com.vrbsm.challenge.ui.view.search.SearchResultsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class HomeFragment extends AbstractFragment implements MovieViewPagerAdapter.OnPageSelectedListener {

    @BindView(R.id.movie_list_main)
    ListView listView;
    @BindView(R.id.movie_view_pager_main)
    ViewPager mMoviesViewPager;
    private List<Movie> mMovieList;
    private BaseAdapter mAdapter;
    private MovieViewPagerAdapter mAdapterViewPager;
    public static final String MOVIE_ARGS = "MOVIE";
    public static final int HOME_REQUEST_CODE = 128;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);

        mMovieList = getMovies();
        callListView(mMovieList, listView);
        callViewPager(mMovieList, mAdapterViewPager, mMoviesViewPager);
        setHasOptionsMenu(true);

        return view;
    }


    private void goDescription(Movie movie) {
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        intent.putExtra(MOVIE_ARGS, movie.getImdbid());
        startActivityForResult(intent,HOME_REQUEST_CODE);
    }

    @OnItemClick(R.id.movie_list_main)
    void onItemSelectedList(int position) {
        goDescription(mMovieList.get(position));
    }


    private void callViewPager(List<Movie> list, MovieViewPagerAdapter adapter, ViewPager viewPager) {
        adapter = new MovieViewPagerAdapter(getContext(), list, this);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(100, 0, 100, 0);
        viewPager.setPageMargin(30);
        viewPager.setAdapter(adapter);

    }

    private void callListView(List<Movie> list, ListView listView) {
        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);
    }

    private List<Movie> getMovies() {
        List<Movie> list = Movie.listAll(Movie.class);
        return list;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == HOME_REQUEST_CODE) {
            mMovieList = getMovies();
            callListView(mMovieList, listView);
            callViewPager(mMovieList, mAdapterViewPager, mMoviesViewPager);
        }
    }
    private void goSearch(){
        Intent intent = new Intent(getActivity(), SearchResultsActivity.class);
        startActivityForResult(intent, HOME_REQUEST_CODE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                goSearch();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageSelected(Movie movie) {
        goDescription(movie);
    }
}
