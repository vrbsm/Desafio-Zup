package br.com.vrbsm.challenge.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vmascare on 01/12/17.
 */

public class MovieViewPagerAdapter extends PagerAdapter {


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Movie> mMovie;
    private OnPageSelectedListener mListener;

    @BindView(R.id.img_view_pager)
    ImageView mImageView;
    @BindView(R.id.tx_name_view_pager)
    TextView mTextView;

    public MovieViewPagerAdapter(Context mContext, List<Movie> mMovie, OnPageSelectedListener listener) {
        this.mContext = mContext;
        this.mMovie = mMovie;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mMovie.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = mLayoutInflater.inflate(R.layout.item_movie_view_pager, container, false);
        onBind(view);
        mTextView.setText(mMovie.get(position).getTitle());
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onPageSelected(mMovie.get(position));
            }
        });
        container.addView(view);
        return view;
    }

    private void onBind(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public interface OnPageSelectedListener {
        void onPageSelected(Movie movie);

    }
}