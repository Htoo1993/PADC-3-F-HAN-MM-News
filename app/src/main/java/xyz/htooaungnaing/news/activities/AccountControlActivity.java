package xyz.htooaungnaing.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.delegates.LoginScreenDelegate;
import xyz.htooaungnaing.news.delegates.LoginUserDeletgate;
import xyz.htooaungnaing.news.fragments.LoginFragment;
import xyz.htooaungnaing.news.fragments.RegisterFragment;

/**
 * Created by htoo on 1/20/2018.
 */

public class AccountControlActivity extends BaseActivity implements LoginScreenDelegate {

    private static final String IE_SCREEN_TYPE = "IE_SCREEN_TYPE";
    private static final int SCREEN_TYPE_LOGIN = 1;
    private static final int SCREEN_TYPE_REGISTER = 2;

    public static Intent newIntentLogin(Context context){
        Intent intent = new Intent(context,AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE, SCREEN_TYPE_LOGIN);
        return intent;
    }
    public static Intent newIntentRegister(Context context){
        Intent intent = new Intent(context,AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE,SCREEN_TYPE_REGISTER);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);

        int screenType = getIntent().getIntExtra(IE_SCREEN_TYPE, -1);
        if (screenType == SCREEN_TYPE_LOGIN){
            // to call login fragment when click login button in view pod
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container,new LoginFragment())
                    .commit();
        } else if (screenType == SCREEN_TYPE_REGISTER){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, new RegisterFragment())
                    .commit();
        }


    }

    @Override
    public void onTapToRegister() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fl_container, new RegisterFragment())
                .addToBackStack("ToRegister")
                .commit();
    }
}
