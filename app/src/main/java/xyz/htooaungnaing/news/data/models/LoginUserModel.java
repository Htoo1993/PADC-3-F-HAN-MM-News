package xyz.htooaungnaing.news.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.htooaungnaing.news.data.vo.LoginUserVO;
import xyz.htooaungnaing.news.events.SuccessLoginEvent;
import xyz.htooaungnaing.news.network.NewsDataAgent;
import xyz.htooaungnaing.news.network.RetrofitDataAgent;

/**
 * Created by htoo on 1/20/2018.
 */

public class LoginUserModel {
    private static LoginUserModel sObjInstance;
    private NewsDataAgent mNewsDataAgent;

    private LoginUserVO mLoginUser;

    private LoginUserModel(){
        mNewsDataAgent = RetrofitDataAgent.getsObjInstance();

        EventBus.getDefault().register(this);
    }

    public static LoginUserModel getsObjInstance() {
        if(sObjInstance == null){
            sObjInstance = new LoginUserModel();
        }
        return sObjInstance;
    }

    public void loginUser(String phoneNo, String password){
        mNewsDataAgent.loadLoginUser(phoneNo, password);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        mLoginUser = event.getLoginUser();
    }
}
