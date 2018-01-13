package xyz.htooaungnaing.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.data.vo.NewsVO;
import xyz.htooaungnaing.news.delegates.NewsActionDelegate;

/**
 * Created by htoo on 12/3/2017.
 */

public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

    private NewsActionDelegate mNewsActionDelegate;

    private NewsVO mNews;

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_posted_date)
    TextView tvPostedDate;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationlogo;

    @BindView(R.id.iv_news)
    ImageView ivNews;

    public ItemNewsViewHolder(View itemView, NewsActionDelegate newsActionDelegate) {
        super(itemView);

        //params (first : this, second : view_object)
        ButterKnife.bind(this,itemView);

        mNewsActionDelegate = newsActionDelegate;
    }

    @OnClick(R.id.cv_news_item_root)
    public void onNewsItemTap(View view){
//        Toast.makeText(view.getContext(), "News item click", Toast.LENGTH_SHORT).show();
        mNewsActionDelegate.onTapNewsItem(mNews);
    }

    public void setNews(NewsVO news){
        mNews = news;

        tvPublicationTitle.setText(news.getPublication().getTitle());
        tvPostedDate.setText(news.getPostedDate());
        tvNewsBrief.setText(news.getBrief());

        Glide.with(ivPublicationlogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationlogo);

        if (news.getImages() != null) {
            ivNews.setVisibility(View.VISIBLE);
            Glide.with(ivNews.getContext())
                    .load(news.getImages().get(0))
                    .into(ivNews);
        } else {
            ivNews.setVisibility(View.GONE);
        }

    }
}
