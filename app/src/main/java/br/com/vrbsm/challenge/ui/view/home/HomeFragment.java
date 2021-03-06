package br.com.vrbsm.challenge.ui.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.ui.adapter.MovieHomeListAdapter;
import br.com.vrbsm.challenge.ui.adapter.MovieViewPagerAdapter;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class HomeFragment extends AbstractFragment implements MovieViewPagerAdapter.OnPageSelectedListener, HomeContract.View {

    @BindView(R.id.movie_list_main)
    ListView listView;
    @BindView(R.id.movie_view_pager_main)
    ViewPager mMoviesViewPager;
    @BindView(R.id.empty_view)
    TextView emptyView;
    private List<Movie> mMovieList;
    private MovieHomeListAdapter mAdapter;
    private MovieViewPagerAdapter mAdapterViewPager;

    private HomeContract.Presenter mPresenter;
    public static final String MOVIE_ARGS = "MOVIE";
    public static final int HOME_REQUEST_CODE = 128;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HomePresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);

        mMovieList = getMovies();
        callViewPager(mMovieList);
        callListView(mMovieList);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void goDescription(Movie movie) {
        mPresenter.goDescription(this, MOVIE_ARGS, HOME_REQUEST_CODE, movie);
    }

    @OnItemClick(R.id.movie_list_main)
    void onItemSelectedList(int position) {
        goDescription(mMovieList.get(position));
    }


    private void callViewPager(List<Movie> list) {
        mAdapterViewPager = new MovieViewPagerAdapter(getContext(), list, this);
        mMoviesViewPager.setClipToPadding(false);
        mMoviesViewPager.setPadding(100, 0, 100, 0);
        mMoviesViewPager.setPageMargin(30);
        mMoviesViewPager.setAdapter(mAdapterViewPager);
        mMoviesViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mAdapter.setPosition(position);
                mAdapter.notifyDataSetChanged();
                listView.setSelection(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void callListView(List<Movie> list) {
        mAdapter = new MovieHomeListAdapter(list);
        listView.setAdapter(mAdapter);
        listView.setEmptyView(emptyView);
    }

    private List<Movie> getMovies() {

        return mPresenter.movieSearchListDB();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(getClass().getCanonicalName(), String.valueOf(requestCode));

        if (requestCode == HOME_REQUEST_CODE) {
            mMovieList = getMovies();
            callListView(mMovieList);
            callViewPager(mMovieList);
        }
    }

    @Override
    public void goSearch() {
        mPresenter.goSearch(this, HOME_REQUEST_CODE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
