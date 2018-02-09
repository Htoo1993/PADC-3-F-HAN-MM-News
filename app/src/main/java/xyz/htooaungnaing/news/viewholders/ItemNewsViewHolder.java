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

    @BindView(R.id.tv_likes)
    TextView tvLikes;

    @BindView(R.id.tv_comments)
    TextView tvComments;

    @BindView(R.id.tv_sent_tos)
    TextView tvSentTos;


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

    @OnClick(R.id.fl_send_to)
    public void onTapSendTo(View view){
        mNewsActionDelegate.onTapSendToButton(mNews);
    }

    @OnClick(R.id.tv_likes)
    public void onTapLikeUsers(View v){
        mNewsActionDelegate.onTapLikeUsers(mNews);
    }

    @OnClick(R.id.tv_comments)
    public void onTapCommentUsers(View v){
        mNewsActionDelegate.onTapCommentUsers(mNews);
    }

    @OnClick(R.id.tv_sent_tos)
    public void onTapSentToUsers(View v){
        mNewsActionDelegate.onTapSentToUsers(mNews);
    }

    @OnClick(R.id.fl_comment)
    public void onTapSendComment(View view){
        mNewsActionDelegate.onTapCommentButton();
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

        tvLikes.setText(tvLikes.getContext().getResources().getString(R.string.format_like_users,
                news.getFavorites().size()));

        tvComments.setText(tvComments.getContext().getResources().getString(R.string.format_comment_users,
                news.getComments().size()));

        tvSentTos.setText(tvSentTos.getContext().getResources().getString(R.string.format_sent_tos_users,
                news.getSendTos().size()));


    }
}
