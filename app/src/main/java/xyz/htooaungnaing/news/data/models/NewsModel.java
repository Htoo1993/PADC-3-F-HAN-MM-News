package xyz.htooaungnaing.news.data.models;

import okhttp3.OkHttpClient;
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

    private NewsModel(){
//        mDataAgent = HttpUrlConnectionDataAgent.getsObjInstance();
//        mDataAgent = OkHttpDataAgent.getsObjInstance();
        mDataAgent = RetrofitDataAgent.getsObjInstance();
    }

    public static NewsModel getsObjInstance(){
        if (sObjInstance == null){
            sObjInstance = new NewsModel();
        }
        return sObjInstance;
    }

    /**
     * load news from network
     */
    public void loadNews(){
        mDataAgent.loadNews();
    }
}
