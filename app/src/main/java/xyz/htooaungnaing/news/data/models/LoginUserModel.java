package xyz.htooaungnaing.news.data.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.htooaungnaing.news.data.vo.LoginUserVO;
import xyz.htooaungnaing.news.events.SuccessLoginEvent;
import xyz.htooaungnaing.news.network.NewsDataAgent;
import xyz.htooaungnaing.news.network.RetrofitDataAgent;
import xyz.htooaungnaing.news.utils.AppConstants;

/**
 * Created by htoo on 1/20/2018.
 */

public class LoginUserModel {
    private static LoginUserModel sObjInstance;
    private NewsDataAgent mNewsDataAgent;

    private LoginUserVO mLoginUser;

    private LoginUserModel(Context context){
        mNewsDataAgent = RetrofitDataAgent.getsObjInstance();
        EventBus.getDefault().register(this);

        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP, Context.MODE_PRIVATE);
        int loginUserId = sharedPreferences.getInt(AppConstants.LOGIN_USER_ID,-1);
        if (loginUserId != -1){
            //user has already logged in
            String name = sharedPreferences.getString(AppConstants.LOGIN_USER_NAME, null);
            String email = sharedPreferences.getString(AppConstants.LOGIN_USER_EMAIL, null);
            String phone = sharedPreferences.getString(AppConstants.LOGIN_USER_PHONE, null);
            String profileUrl = sharedPreferences.getString(AppConstants.LOGIN_USER_PROFILE_URL, null);
            String coverUrl = sharedPreferences.getString(AppConstants.LOGIN_USER_COVER_URL, null);

            mLoginUser = new LoginUserVO(loginUserId,name,email,phone,profileUrl,coverUrl);
        }
    }

    public static LoginUserModel getsObjInstance(Context context) {
        if(sObjInstance == null){
            sObjInstance = new LoginUserModel(context);
        }
        return sObjInstance;
    }

    public void loginUser(Context context, String phoneNo, String password){
        mNewsDataAgent.loadLoginUser(context, phoneNo, password);
    }

    public boolean isUserLogin(){
        return mLoginUser != null;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        mLoginUser = event.getLoginUser();
        // Save user data in SharedPreferences
        SharedPreferences sharedPreferences = event.getContext().getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AppConstants.LOGIN_USER_ID, event.getLoginUser().getUserId());
        editor.putString(AppConstants.LOGIN_USER_NAME, event.getLoginUser().getName());
        editor.putString(AppConstants.LOGIN_USER_EMAIL, event.getLoginUser().getEmail());
        editor.putString(AppConstants.LOGIN_USER_PHONE, event.getLoginUser().getPhoneNo());
        editor.putString(AppConstants.LOGIN_USER_PROFILE_URL, event.getLoginUser().getProfileUrl());
        editor.putString(AppConstants.LOGIN_USER_COVER_URL, event.getLoginUser().getCoverUrl());

//        editor.commit();
        editor.apply();
    }

    public void logout(){
        mLoginUser = null;

        UserLogoutEvent event = new UserLogoutEvent();
        EventBus.getDefault().post(event);
    }
}
