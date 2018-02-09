package xyz.htooaungnaing.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import xyz.htooaungnaing.news.R;

/**
 * Created by htoo on 2/10/2018.
 */

public class AddCommentDialog extends Dialog {

    public AddCommentDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_add_comment);
        setCancelable(false);

        ButterKnife.bind(this,this);
    }


}
