package xyz.htooaungnaing.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.viewholders.ItemSportsNewsViewHolders;

/**
 * Created by htoo on 1/10/2018.
 */

public class SportsNewsAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_sports_news,parent,false);
        ItemSportsNewsViewHolders itemSportsNewsViewHolders = new ItemSportsNewsViewHolders(view);

        return itemSportsNewsViewHolders;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
