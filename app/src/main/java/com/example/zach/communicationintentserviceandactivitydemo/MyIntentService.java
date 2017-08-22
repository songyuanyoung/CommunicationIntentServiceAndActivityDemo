package com.example.zach.communicationintentserviceandactivitydemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by zhangwenpurdue on 8/22/2017.
 */

public class MyIntentService extends IntentService {
    public static final String ACTION_MyIntentService = "com.example.androidintentservice.RESPONSE";
    public static final String EXTRA_KEY_IN = "EXTRA_IN";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    String msgFromActivity;
    String extraOut;

    public MyIntentService() {
        super("com.example.androidintentservice.MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        msgFromActivity = intent.getStringExtra(EXTRA_KEY_IN);
        extraOut = "This message is from IntentService" +  msgFromActivity;

        for(int i = 0; i <=3; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        Intent intentResponse = new Intent();
        intentResponse.setAction(ACTION_MyIntentService);
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        intentResponse.putExtra(EXTRA_KEY_OUT, extraOut);
        sendBroadcast(intentResponse);
    }

}
