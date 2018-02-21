package xyz.htooaungnaing.news.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

import xyz.htooaungnaing.news.MMNewsApp;

/**
 * Created by htoo on 2/10/2018.
 */

public class SampleService extends IntentService {

    private static final String IE_TIMESTAMP = "timestamp";

    public SampleService() {
        super("SampleService");
    }

    public static Intent newIntent(Context context, String timestamp){
        Intent intent = new Intent(context, SampleService.class);
        intent.putExtra(IE_TIMESTAMP , timestamp);
        return intent;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // logic background running
        String timestamp ="";
        if (intent != null){
            timestamp = intent.getStringExtra(IE_TIMESTAMP);
        }
        Log.d(MMNewsApp.LOG_TAG, "SampleService : onHandleIntent" + timestamp);
    }
}
