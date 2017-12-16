package xyz.htooaungnaing.news.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.adapters.ImagesInNewsDetailsAdapter;

/**
 * Created by htoo on 12/9/2017.
 */

public class NewsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_news_details)
    Toolbar toolbarNewsDetails;

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    private ImagesInNewsDetailsAdapter mImagesInNewsDetailsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ButterKnife.bind(this,this);

//        setSupportActionBar(toolbarNewsDetails);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);

        mImagesInNewsDetailsAdapter = new ImagesInNewsDetailsAdapter();

        vpNewsDetailsImages.setAdapter(mImagesInNewsDetailsAdapter);

    }
}