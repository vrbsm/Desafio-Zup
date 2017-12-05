package br.com.vrbsm.challenge.ui.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.util.glide.GlideApp;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends BaseAdapter {

    private List<Movie> mList;

    public MovieListAdapter(List<Movie> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        if (mList.get(i).getYear() != null)
            holder.title.setText(mList.get(i).getTitle() + " ( " + mList.get(i).getYear() + " ) ");
        else
            holder.title.setText(mList.get(i).getTitle());

        if (mList.get(i).getUrlImage() != null)
            if (!mList.get(i).getUrlImage().equals("N/A")) {
                GlideApp.with(view.getContext()).load(mList.get(i).getUrlImage())
                        .error(R.drawable.place_holder)
                        .placeholder(R.drawable.place_holder)
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                holder.title.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
                            }
                        });
            } else {
                holder.title.setCompoundDrawablesWithIntrinsicBounds(view.getContext().getResources().getDrawable(R.drawable.place_holder), null, null, null);
            }

        return view;
    }

    public class ViewHolder {
        @BindView(R.id.tx_name_movie_item)
        TextView title;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
