package xyz.htooaungnaing.news.data.models;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.htooaungnaing.news.data.vo.LoginUserVO;
import xyz.htooaungnaing.news.events.RegisterEvent;
import xyz.htooaungnaing.news.network.NewsDataAgent;
import xyz.htooaungnaing.news.network.RetrofitDataAgent;

/**
 * Created by htoo on 1/21/2018.
 */

public class RegisterModel {

    private static RegisterModel sObjInstance;
    private NewsDataAgent mNewsDataAgent;
    private LoginUserVO mRegisterUser;

    private RegisterModel(){
        mNewsDataAgent = RetrofitDataAgent.getsObjInstance();
    }

    public static RegisterModel getsObjInstance() {
        if(sObjInstance == null){
            sObjInstance = new RegisterModel();
        }
        return sObjInstance;
    }

    public void registerUser(String phoneNo, String password, String name){
        mNewsDataAgent.register(phoneNo, password, name);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onRegisterSuccess(RegisterEvent event){
        mRegisterUser = event.getRegisterUser();
    }
}
