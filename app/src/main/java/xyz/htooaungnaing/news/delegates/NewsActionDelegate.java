package xyz.htooaungnaing.news.delegates;

import xyz.htooaungnaing.news.data.vo.NewsVO;

/**
 * Created by htoo on 12/17/2017.
 */

public interface NewsActionDelegate {

    void onTapNewsItem(NewsVO tappedNews);

    void onTapCommentButton();

    void onTapSendToButton(NewsVO news);

    void onTapFavoriteButton();

    void onTapLikeUsers(NewsVO tappedNews);

    void onTapCommentUsers(NewsVO tappedNews);

    void onTapSentToUsers(NewsVO tappedNews);
}
