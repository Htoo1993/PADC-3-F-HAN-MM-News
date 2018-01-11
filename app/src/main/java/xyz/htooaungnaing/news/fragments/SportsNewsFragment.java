package xyz.htooaungnaing.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.adapters.SportsNewsAdapter;

/**
 * Created by htoo on 1/7/2018.
 */

public class SportsNewsFragment extends Fragment {

    @BindView(R.id.rv_sports_news)
    RecyclerView rvSportsNews;

    private SportsNewsAdapter mSportsNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports_news,container,false);

        ButterKnife.bind(this,view);

        LinearLayoutManager sportsNewsLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mSportsNewsAdapter = new SportsNewsAdapter();
        rvSportsNews.setLayoutManager(sportsNewsLayoutManager);
        rvSportsNews.setAdapter(mSportsNewsAdapter);

        return view;
    }
}
