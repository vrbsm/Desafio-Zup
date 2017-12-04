package br.com.vrbsm.challenge.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.vrbsm.challenge.R;
import br.com.vrbsm.challenge.model.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vmascare on 30/11/17.
 */

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
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        }else{
            holder = (ViewHolder)view.getTag();
        }

        holder.title.setText(mList.get(i).getTitle());

        return view;
    }

   public class ViewHolder{
       @BindView(R.id.tx_name_movie_item) TextView title;

       public ViewHolder(View view) {
           ButterKnife.bind(this,view);
       }
   }
}
