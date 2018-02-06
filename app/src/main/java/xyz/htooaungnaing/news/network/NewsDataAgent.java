package xyz.htooaungnaing.news.network;

import android.content.Context;

/**
 * Created by htoo on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news from network api
     */
    void loadNews();

    /**
     * @param context
     * @param phoneNo
     * @param password
     */
    void loadLoginUser(Context context, String phoneNo, String password);

    /**
     *
     * @param phoneNo
     * @param password
     * @param name
     */
    void register(String phoneNo, String password, String name);
}
