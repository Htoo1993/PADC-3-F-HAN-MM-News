package xyz.htooaungnaing.news.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.R;

/**
 * Created by htoo on 2/3/2018.
 */

public class UserProfileActivity extends BaseActivity {

    @BindView(R.id.iv_profile_image)
    ImageView ivUserProfile;

    @BindView(R.id.iv_profile_cover)
    ImageView ivProfileCover;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ButterKnife.bind(this, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== 234){
            Uri orignalUri = data.getData();
            Glide.with(getApplicationContext())
                    .load(orignalUri)
                    .into(ivUserProfile);
        } else if(requestCode == 345){
            Bundle extras = data.getExtras();
            Bitmap takenPicture = (Bitmap) extras.get("data");
            ivProfileCover.setImageBitmap(takenPicture);
        }
    }

    @OnClick(R.id.iv_edit_profile)
    public void onTapEditProfileImage(View v){
        //Toast.makeText(getApplicationContext(), "Tab Edit Profile", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        //to accept image type _ write with mime type
        intent.setType("image/*");
        startActivityForResult(intent,234);
    }

    @OnClick(R.id.iv_edit_cover)
    public void onTapEditCoverImage(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 345);
    }
}
