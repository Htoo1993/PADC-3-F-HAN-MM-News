package xyz.htooaungnaing.news.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.data.vo.FavoriteVO;

/**
 * Created by htoo on 2/9/2018.
 */

public class FavoriteUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone_no)
    TextView tvPhoneNo;

    @BindView(R.id.tv_timestamp)
    TextView tvTimeStamp;

    public FavoriteUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setData(FavoriteVO favorite){
        Glide.with(itemView.getContext())
                .load(favorite.getActedUser().getProfileImage())
                .into(ivUserProfile);

        tvName.setText(favorite.getActedUser().getUserName());

        String originalTimeFormat = favorite.getFavoriteDate();
        SimpleDateFormat sdfOriginalFormat = new SimpleDateFormat("E MMM dd hh:mm:ss Z yyyy");
        try {
            Date date = sdfOriginalFormat.parse(originalTimeFormat);
            SimpleDateFormat sdfPresentableFormat = new SimpleDateFormat("hh:mm a',' MMM dd yyyy");
            String presentableTimeformat = sdfPresentableFormat.format(date);
            tvTimeStamp.setText(presentableTimeformat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
