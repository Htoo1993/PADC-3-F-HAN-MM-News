package xyz.htooaungnaing.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.htooaungnaing.news.R;
import xyz.htooaungnaing.news.adapters.FavoriteAdapter;
import xyz.htooaungnaing.news.data.vo.FavoriteVO;

/**
 * Created by htoo on 2/9/2018.
 */

public class LikeUsersDialog extends Dialog {

    @BindView(R.id.rv_like_users)
    RecyclerView rvLikeUsers;

    private FavoriteAdapter mFavoriteAdapter;

    public LikeUsersDialog(@NonNull Context context, List<FavoriteVO> likeUserList) {
        super(context);
        setContentView(R.layout.dialog_like_users);
        ButterKnife.bind(this,this);

        setCancelable(false);

        mFavoriteAdapter = new FavoriteAdapter();
        rvLikeUsers.setAdapter(mFavoriteAdapter);
        rvLikeUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        mFavoriteAdapter.setData(likeUserList);
    }

    @OnClick(R.id.iv_close_dialog)
    public void onTapCloseDialog(View view){
        dismiss();
    }


}
