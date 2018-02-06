package xyz.htooaungnaing.news.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.data.models.LoginUserModel;
import xyz.htooaungnaing.news.delegates.LoginScreenDelegate;
import xyz.htooaungnaing.news.events.SuccessLoginEvent;

/**
 * Created by htoo on 1/20/2018.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.et_email_or_phone)
    EditText etEmailOrPhone;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_forget_password)
    TextView btnForgetPassword;

    private LoginScreenDelegate mLoginScreenDelegate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginScreenDelegate = (LoginScreenDelegate) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_to_login)
    public void onLogin(View v){
        String emailOrPhone = etEmailOrPhone.getText().toString();
        String password = etPassword.getText().toString();

        LoginUserModel.getsObjInstance(getContext()).loginUser(getContext(), emailOrPhone, password);


    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View v){
        mLoginScreenDelegate.onTapToRegister();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        if(getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}
