package xyz.htooaungnaing.news.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.htooaungnaing.news.network.responses.GetNewsResponse;

/**
 * Created by htoo on 1/6/2018.
 */

public interface NewsApi {

    @FormUrlEncoded
    @POST("getMMNews.php")
    Call<GetNewsResponse> getNews(@Field("page") int page,
                                  @Field("access_token") String accessToken); // parameter api's request parameter

}
