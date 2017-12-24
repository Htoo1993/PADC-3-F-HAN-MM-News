package xyz.htooaungnaing.news.network.responses;

import java.util.List;

import xyz.htooaungnaing.news.data.vo.NewsVO;

/**
 * Created by htoo on 12/24/2017.
 */

public class GetNewsResponse {

    private int code;
    private String message;
    private String apiVersion;
    private String page;
    private List<NewsVO> mmNews;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewsVO> getMmNews() {
        return mmNews;
    }
}
