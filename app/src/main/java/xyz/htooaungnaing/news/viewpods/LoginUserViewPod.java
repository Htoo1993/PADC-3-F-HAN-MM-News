package xyz.htooaungnaing.news.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import javax.microedition.khronos.opengles.GL;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.data.vo.LoginUserVO;
import xyz.htooaungnaing.news.delegates.LoginUserDeletgate;

/**
 * Created by htoo on 1/14/2018.
 */

public class LoginUserViewPod extends RelativeLayout {

    @BindView(R.id.iv_user_background)
    ImageView ivUserBackground;

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_login_user_phone)
    TextView tvLoginUserPhone;


    private LoginUserDeletgate mLoginUserDeletgate;

    public LoginUserViewPod(Context context) {
        super(context);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    public void bindData(LoginUserVO loginUser){

        Glide.with(ivUserProfile.getContext())
                .load(loginUser.getProfileUrl())
                .into(ivUserProfile);

        Glide.with(ivUserBackground.getContext())
                .load(loginUser.getCoverUrl())
                .into(ivUserBackground);

        tvName.setText(loginUser.getName());
        tvLoginUserPhone.setText(loginUser.getPhoneNo());
    }

    public void setDelegate(LoginUserDeletgate loginUserDeletgate){
        mLoginUserDeletgate = loginUserDeletgate;
    }

    @OnClick(R.id.btn_logout)
    public void onTapLogout(View view){
        mLoginUserDeletgate.onTapLogout();
    }
}
