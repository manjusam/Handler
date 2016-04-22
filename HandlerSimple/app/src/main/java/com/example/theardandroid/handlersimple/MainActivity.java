package com.example.theardandroid.handlersimple;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {
     Handler handler;
     ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress= (ProgressBar) findViewById(R.id.progressBar);
        Thread thread = new Thread(new MyThread());
        thread.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
              //  msg=new Message();
                progress.setProgress(msg.arg1);
            }
        };



    }

   private class  MyThread implements Runnable
    {
    Message message;
    // looper is cretaed for this background thread with message queue
      @Override

        public void run() {
            for(int i=0;i<100;i++)
            {
                message = message.obtain();
                message.arg1=i;
                handler.sendMessage(message);
                try {
                    //have to give this thread.sleep (particular time) otherwise it will give low memory killer error.

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("Message","   " +i);
            }
        }
    }
}
