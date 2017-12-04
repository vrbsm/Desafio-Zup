package br.com.vrbsm.challenge.ui.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.vrbsm.challenge.BuildConfig;
import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.api.MovieApi;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.model.Search;
import br.com.vrbsm.challenge.rest.RestGenerator;
import br.com.vrbsm.challenge.ui.adapter.MovieListAdapter;
import br.com.vrbsm.challenge.ui.adapter.MovieViewPagerAdapter;
import br.com.vrbsm.challenge.ui.view.AbstractActivity;
import br.com.vrbsm.challenge.ui.view.AbstractFragment;
import br.com.vrbsm.challenge.ui.view.description.DescriptionActivity;
import br.com.vrbsm.challenge.util.controlfrags.ControlFrags;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends AbstractFragment implements MovieViewPagerAdapter.OnPageSelectedListener {

    @BindView(R.id.movie_list_main) ListView listView;
    @BindView(R.id.movie_view_pager_main) ViewPager mMoviesViewPager;
    private List<Movie> mMovieList;
    private BaseAdapter mAdapter;
    private PagerAdapter mAdapterViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);

        mMovieList = mockList();
        callListView(mMovieList, mAdapter, listView);
        callViewPager(mMovieList, mAdapterViewPager , mMoviesViewPager);


        return view;
    }
    private void goDescription(){
        Intent intent = new Intent(getActivity(), DescriptionActivity.class);
        startActivity(intent);
    }

    @OnItemClick(R.id.movie_list_main)
    void onItemSelectedList(int position){
        goDescription();
    }



    private void callViewPager(List<Movie> list, PagerAdapter baseAdapter, ViewPager viewPager){
        baseAdapter = new MovieViewPagerAdapter(getContext(), list, this);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(100,0,100,0);
        viewPager.setPageMargin(30);
        viewPager.setAdapter(baseAdapter);

    }
    private void callListView(List<Movie> list, BaseAdapter baseAdapter, ListView listView){
        baseAdapter = new MovieListAdapter(list);
        listView.setAdapter(baseAdapter);
    }

    private List<Movie> mockList() {
        List<Movie> list = new ArrayList<Movie>();
        Movie movie = new Movie();
        movie.setTitle("Victor Mascarenhas");
        movie.setUrlImage("http://victor.com.br");
        list.add(movie);
        list.add(movie);
        list.add(movie);
        return list;
    }

    @Override
    public void onPageSelected(Movie movie) {
            goDescription();
    }
}
