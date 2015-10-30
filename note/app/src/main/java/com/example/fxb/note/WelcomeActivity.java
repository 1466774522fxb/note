package com.example.fxb.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by FXB on 2015/7/23.
 */
public class WelcomeActivity extends Activity{
    private static final long DELAY_MILLIS = 2 * 1000;
    private static final int MSG_WHAT_STARTMAIN = 1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==MSG_WHAT_STARTMAIN){
                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        System.out.println("----------------------------------Welcome-");
        handler.sendEmptyMessageDelayed(MSG_WHAT_STARTMAIN,DELAY_MILLIS);

    }
}
