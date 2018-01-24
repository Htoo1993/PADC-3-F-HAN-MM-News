package xyz.htooaungnaing.news.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.htooaungnaing.news.network.responses.GetLoginUserResponse;
import xyz.htooaungnaing.news.network.responses.GetNewsResponse;
import xyz.htooaungnaing.news.network.responses.RegisterResponse;

/**
 * Created by htoo on 1/6/2018.
 */

public interface NewsApi {

    @FormUrlEncoded
    @POST("getMMNews.php")
    Call<GetNewsResponse> getNews(@Field("page") int page,
                                  @Field("access_token") String accessToken); // parameter api's request parameter

    @FormUrlEncoded
    @POST("login.php")
    Call<GetLoginUserResponse> getLoginUser(@Field("phoneNo") String phoneNo,
                                            @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(@Field("phoneNo") String phoneNo,
                                    @Field("password") String password,
                                    @Field("name") String name);

}
