package xyz.htooaungnaing.news.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.adapters.ImagesInNewsDetailsAdapter;
import xyz.htooaungnaing.news.data.models.NewsModel;
import xyz.htooaungnaing.news.data.vo.NewsVO;

/**
 * Created by htoo on 12/9/2017.
 */

public class NewsDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar_news_details)
    Toolbar toolbarNewsDetails;

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;

    @BindView(R.id.tv_publication_date)
    TextView tvPublicationDate;

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

        String newsId = getIntent().getStringExtra("news_id");
        NewsVO news = NewsModel.getsObjInstance().getNewsById(newsId);

        bindData(news);
    }

    private void bindData(NewsVO news){
        Glide.with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);
        tvPublicationTitle.setText(news.getPublication().getTitle());
        tvPublicationDate.setText(news.getPostedDate());
        tvNewsDetails.setText(news.getDetails());
    }
}
