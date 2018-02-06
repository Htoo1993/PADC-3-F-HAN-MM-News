package xyz.htooaungnaing.news.viewpods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.data.models.LoginUserModel;
import xyz.htooaungnaing.news.data.models.UserLogoutEvent;
import xyz.htooaungnaing.news.delegates.BeforeLoginDelegate;
import xyz.htooaungnaing.news.delegates.LoginUserDeletgate;
import xyz.htooaungnaing.news.events.SuccessLoginEvent;

/**
 * Created by htoo on 1/21/2018.
 */

public class AccountControlViewPod extends FrameLayout {

    @BindView(R.id.vp_before_login)
    BeforeLoginUserViewPod vpBeforeLogin;

    @BindView(R.id.vp_login_user)
    LoginUserViewPod vpLoginUser;

    private LoginUserDeletgate mLoginUserDeletgate;

    public AccountControlViewPod(@NonNull Context context) {
        super(context);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);

        refreshUserSession();

        EventBus.getDefault().register(this);
    }

    public void setDelegate(BeforeLoginDelegate beforeLoginDelegate){
        vpBeforeLogin.setDelegate(beforeLoginDelegate);
    }

    public void setDelegate(LoginUserDeletgate loginUserDeletgate){
        vpLoginUser.setDelegate(loginUserDeletgate);
        mLoginUserDeletgate = loginUserDeletgate;
    }

    @OnClick(R.id.vp_login_user)
    public void onTapLoginUser(View view){
        mLoginUserDeletgate.onTapLoginuser();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        vpBeforeLogin.setVisibility(View.GONE);
        vpLoginUser.setVisibility(View.VISIBLE);

        vpLoginUser.bindData(event.getLoginUser());
    }

    private void refreshUserSession(){
        if(LoginUserModel.getsObjInstance(getContext()).isUserLogin()){
            vpBeforeLogin.setVisibility(View.GONE);
            vpLoginUser.setVisibility(View.VISIBLE);
        } else {
            vpBeforeLogin.setVisibility(View.VISIBLE);
            vpLoginUser.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutUser(UserLogoutEvent event){
        vpBeforeLogin.setVisibility(View.VISIBLE);
        vpLoginUser.setVisibility(View.GONE);
    }
}
