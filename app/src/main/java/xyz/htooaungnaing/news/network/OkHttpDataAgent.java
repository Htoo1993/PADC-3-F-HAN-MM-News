package xyz.htooaungnaing.news.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xyz.htooaungnaing.news.MMNewsApp;
import xyz.htooaungnaing.news.data.vo.NewsVO;
import xyz.htooaungnaing.news.events.LoadedNewsEvent;
import xyz.htooaungnaing.news.network.responses.GetNewsResponse;

/**
 * Created by htoo on 1/6/2018.
 */

public class OkHttpDataAgent implements NewsDataAgent {

    private static OkHttpDataAgent sObjInstance;

    private OkHttpClient mHttpClient;

    private OkHttpDataAgent() {
        mHttpClient = new OkHttpClient.Builder() //1
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgent getsObjInstance() {
        //factory logic
        if (sObjInstance == null) {
            sObjInstance = new OkHttpDataAgent();
        }
        //factory logic

        return sObjInstance;
    }

    @Override
    public void loadNews() {
        new LoadNewsTask(mHttpClient).execute("http://padcmyanmar.com/padc-3/mm-news/apis/v1/getMMNews.php");
    }

    @Override
    public void loadLoginUser(Context context, String phoneNo, String password) {

    }

    @Override
    public void register(String phoneNo, String password, String name) {

    }

    //AsyncTask<Frisrt param : for Url, second param : void, thrid param : json(text)
    private static class LoadNewsTask extends AsyncTask<String, Void, String> {

        private OkHttpClient mHttpClient;

        public LoadNewsTask(OkHttpClient okHttpClient) {
            super();
            mHttpClient = okHttpClient;
        }

        @Override
        protected String doInBackground(String... urls) {
            String url = urls[0];

            RequestBody formBody = new FormBody.Builder() //2
                    .add("access_token", "b002c7e1a528b7cb460933fc2875e916")
                    .add("page", "1")
                    .build();

            Request request = new Request.Builder() //3
                    .url(url)
                    .post(formBody)
                    .build();

            String responseString = null;

            try {
                Response response = mHttpClient.newCall(request).execute(); //4
                if(response.isSuccessful() && response.body() != null){
                    responseString = response.body().string();
                }
            } catch (IOException e) {
                Log.e(MMNewsApp.LOG_TAG,e.getMessage());
            }


            return responseString;
        }

        @Override
        //param String coz doInBackground 3rd param
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            Gson gson = new Gson();
            GetNewsResponse getNewsResponse = gson.fromJson(response, GetNewsResponse.class);

            LoadedNewsEvent event = new LoadedNewsEvent(getNewsResponse.getMmNews());
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(event);
        }
    }
}
