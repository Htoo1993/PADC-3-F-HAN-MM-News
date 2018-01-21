package xyz.htooaungnaing.news.network;

/**
 * Created by htoo on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news from network api
     */
    void loadNews();

    /**
     *
     * @param phoneNo
     * @param password
     */
    void loadLoginUser(String phoneNo, String password);
}
