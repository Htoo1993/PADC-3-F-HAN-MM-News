package xyz.htooaungnaing.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.data.vo.FavoriteVO;
import xyz.htooaungnaing.news.viewholders.FavoriteUserViewHolder;

/**
 * Created by htoo on 2/9/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteUserViewHolder> {

    private List<FavoriteVO> mFavoriteUsers;

    public FavoriteAdapter() {
        mFavoriteUsers = new ArrayList<>();
    }

    @Override
    public FavoriteUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_user, parent, false);

        return new FavoriteUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteUserViewHolder holder, int position) {
        holder.setData(mFavoriteUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mFavoriteUsers.size();
    }

    public void setData(List<FavoriteVO> favoriteList) {
        mFavoriteUsers = favoriteList;
        notifyDataSetChanged();
    }
}
