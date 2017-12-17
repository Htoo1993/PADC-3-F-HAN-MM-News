package xyz.htooaungnaing.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.delegates.NewsActionDelegate;

/**
 * Created by htoo on 12/3/2017.
 */

public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

    private NewsActionDelegate mNewsActionDelegate;

    public ItemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate) {
        super(itemView);

        //params (first : this, second : view_object)
        ButterKnife.bind(this,itemView);

        mNewsActionDelegate = newsActionDelegate;
    }

    @OnClick(R.id.cv_news_item_root)
    public void onNewsItemTap(View view){
//        Toast.makeText(view.getContext(), "News item click", Toast.LENGTH_SHORT).show();
        mNewsActionDelegate.onTapNewsItem();
    }
}
