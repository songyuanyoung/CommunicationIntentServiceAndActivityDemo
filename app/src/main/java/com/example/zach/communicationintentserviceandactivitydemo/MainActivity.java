package com.example.zach.communicationintentserviceandactivitydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textResult;


    private MyBroadcastReceiver myBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResult = (TextView)findViewById(R.id.result);


        String msgToIntentService = "-----Message from mainActivity----";
        textResult.setText(textResult.getText() + msgToIntentService);


        Intent intentMyIntentService = new Intent(this, MyIntentService.class);
        intentMyIntentService.putExtra(MyIntentService.EXTRA_KEY_IN, msgToIntentService);
        startService(intentMyIntentService);

        myBroadcastReceiver = new MyBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MyIntentService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(myBroadcastReceiver, intentFilter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(myBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra(MyIntentService.EXTRA_KEY_OUT);
            textResult.setText(result);
        }
    }


}
