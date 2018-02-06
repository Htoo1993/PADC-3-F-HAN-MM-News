package xyz.htooaungnaing.news.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.MMNewsApp;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.adapters.NewsAdapter;
import xyz.htooaungnaing.news.data.models.LoginUserModel;
import xyz.htooaungnaing.news.data.models.NewsModel;
import xyz.htooaungnaing.news.data.vo.NewsVO;
import xyz.htooaungnaing.news.delegates.BeforeLoginDelegate;
import xyz.htooaungnaing.news.delegates.LoginUserDeletgate;
import xyz.htooaungnaing.news.delegates.NewsActionDelegate;
import xyz.htooaungnaing.news.events.LoadedNewsEvent;
import xyz.htooaungnaing.news.viewpods.AccountControlViewPod;
import xyz.htooaungnaing.news.viewpods.BeforeLoginUserViewPod;
import xyz.htooaungnaing.news.viewpods.EmptyViewPod;

public class MainActivity extends BaseActivity implements NewsActionDelegate, BeforeLoginDelegate, LoginUserDeletgate{

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.in_view_pod)
    EmptyViewPod inViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter mNewsAdapter;

    private BeforeLoginUserViewPod beforeLoginUserViewPod;

    private AccountControlViewPod vpAccountControlViewPod;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the butterknife
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        mNewsAdapter = new NewsAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvNews.setLayoutManager(linearLayoutManager);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        rvNews.setLayoutManager(gridLayoutManager);

        rvNews.setAdapter(mNewsAdapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.all_news);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_news_by_category) {
                    Intent intent = NewsByCategoryActivity.newIntent(getApplicationContext());
                    startActivity(intent);

                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return false;
            }
        });

//        beforeLoginUserViewPod = (BeforeLoginUserViewPod) navigationView.getHeaderView(0);
//        beforeLoginUserViewPod.setDelegate(this);
        vpAccountControlViewPod = (AccountControlViewPod) navigationView.getHeaderView(0);
        vpAccountControlViewPod.setDelegate((BeforeLoginDelegate) this);
        vpAccountControlViewPod.setDelegate((LoginUserDeletgate) this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsModel.getsObjInstance().loadNews();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        //using singleton pattern
        NewsModel.getsObjInstance().loadNews();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait while data is loading");
        mProgressDialog.show();
    }



    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onTapFab(View view) {
        Snackbar.make(view, "Replace with your own action - ButterKnife", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onTapNewsItem(NewsVO tappedNews) {
        // params first : current context, second : target class
        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        intent.putExtra("news_id", tappedNews.getNewsId());
        startActivity(intent);
    }

    @Override
    public void onTapCommentButton() {

    }

    @Override
    public void onTapSendToButton() {

    }

    @Override
    public void onTapFavoriteButton() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsLoaded(LoadedNewsEvent event){
        Log.d(MMNewsApp.LOG_TAG, "onNewsLoaded : " + event.getNewsList().size());
        swipeRefreshLayout.setRefreshing(false);
        mProgressDialog.dismiss();
        if(!event.getNewsList().isEmpty()){
            mNewsAdapter.setNews(event.getNewsList());
            inViewPod.setVisibility(View.GONE);
        }

    }


    @Override
    public void onTapToLogin() {
        Intent intent = AccountControlActivity.newIntentLogin(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapToRegister() {
        Intent intent = AccountControlActivity.newIntentRegister(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapLogout() {
        LoginUserModel.getsObjInstance(getApplicationContext()).logout();
    }

    @Override
    public void onTapLoginuser() {
        Intent intent = UserProfileActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }
}
