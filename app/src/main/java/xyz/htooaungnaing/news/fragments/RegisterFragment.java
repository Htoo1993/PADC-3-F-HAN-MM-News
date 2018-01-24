package xyz.htooaungnaing.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.data.models.RegisterModel;
import xyz.htooaungnaing.news.events.RegisterEvent;

/**
 * Created by htoo on 1/20/2018.
 */

public class RegisterFragment extends Fragment {

    @BindView(R.id.et_register_name)
    EditText etRegisterName;

    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;

    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
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

    @OnClick(R.id.tv_sign_up_as_seller)
    public void register(View v){
        String name = etRegisterName.getText().toString();
        String phone = etRegisterPhone.getText().toString();
        String password = etRegisterPassword.getText().toString();

        RegisterModel.getsObjInstance().registerUser(phone, password, name);


//        Snackbar.make(v,"Clicked Register!!", Snackbar.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterSuccess(RegisterEvent event){
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}
