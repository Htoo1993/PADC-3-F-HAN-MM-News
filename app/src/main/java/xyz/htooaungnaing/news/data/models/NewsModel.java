package xyz.htooaungnaing.news.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import xyz.htooaungnaing.news.data.vo.NewsVO;
import xyz.htooaungnaing.news.events.LoadedNewsEvent;
import xyz.htooaungnaing.news.network.HttpUrlConnectionDataAgent;
import xyz.htooaungnaing.news.network.NewsDataAgent;
import xyz.htooaungnaing.news.network.OkHttpDataAgent;
import xyz.htooaungnaing.news.network.RetrofitDataAgent;

/**
 * Created by htoo on 12/23/2017.
 */

public class NewsModel {

    private static NewsModel sObjInstance;
    private NewsDataAgent mDataAgent;

    private Map<String , NewsVO> mNews;

    private NewsModel(){
//        mDataAgent = HttpUrlConnectionDataAgent.getsObjInstance();
//        mDataAgent = OkHttpDataAgent.getsObjInstance();
        mDataAgent = RetrofitDataAgent.getsObjInstance();

        mNews = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static NewsModel getsObjInstance(){
        if (sObjInstance == null){
            sObjInstance = new NewsModel();
        }
        return sObjInstance;
    }

    /**
     * Get news object by id
     * @param newsId
     * @return
     */
    public NewsVO getNewsById(String newsId){
        return mNews.get(newsId);
    }

    /**
     * load news from network
     */
    public void loadNews(){
        mDataAgent.loadNews();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsLoaded(LoadedNewsEvent event){
        for (NewsVO news : event.getNewsList()){
            mNews.put(news.getNewsId(),news);
        }
    }
}
