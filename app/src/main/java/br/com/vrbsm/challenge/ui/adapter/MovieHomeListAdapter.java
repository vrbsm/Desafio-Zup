package br.com.vrbsm.challenge.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import br.com.vrbsm.challenge.util.glide.GlideApp;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieHomeListAdapter extends BaseAdapter {

    private List<Movie> mList;

    public MovieHomeListAdapter(List<Movie> mList) {
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

    int positionColor;

    public void setPosition(int position) {
        this.positionColor = position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (positionColor == i)
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.colorPrimary));
        else
            view.setBackground(view.getContext().getResources().getDrawable(R.drawable.list_home));

        if (mList.get(i).getYear() != null)
            holder.title.setText(mList.get(i).getTitle() + " ( " + mList.get(i).getYear() + " ) ");
        else
            holder.title.setText(mList.get(i).getTitle());


        return view;
    }

    public class ViewHolder {
        @BindView(android.R.id.text1)
        TextView title;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
