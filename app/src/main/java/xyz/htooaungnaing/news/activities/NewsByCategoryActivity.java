package xyz.htooaungnaing.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.adapters.NewsByCategoryAdapter;
import xyz.htooaungnaing.news.data.models.NewsModel;
import xyz.htooaungnaing.news.fragments.InternationalNewsFragment;
import xyz.htooaungnaing.news.fragments.NewsByCategoryFragment;
import xyz.htooaungnaing.news.fragments.SportsNewsFragment;

/**
 * Created by htoo on 1/7/2018.
 */

public class NewsByCategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.vp_news_by_category)
    ViewPager vpNewsByCategory;

    @BindView(R.id.tb_news_by_category)
    TabLayout tbNewsBycategory;

    private NewsByCategoryAdapter mNewsByCategoryAdapter;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, NewsByCategoryActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_by_category);

        ButterKnife.bind(this,this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_news_by_category);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNewsByCategoryAdapter = new NewsByCategoryAdapter(getSupportFragmentManager());
        vpNewsByCategory.setAdapter(mNewsByCategoryAdapter);

        mNewsByCategoryAdapter.addTab("Local News", new NewsByCategoryFragment());
        mNewsByCategoryAdapter.addTab("International News", new InternationalNewsFragment());
        mNewsByCategoryAdapter.addTab("Sports News", new SportsNewsFragment());

        tbNewsBycategory.setupWithViewPager(vpNewsByCategory);

        NewsModel.getsObjInstance().loadNews();

        vpNewsByCategory.setOffscreenPageLimit(mNewsByCategoryAdapter.getCount());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
