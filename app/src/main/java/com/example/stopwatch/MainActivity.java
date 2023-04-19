package com.example.stopwatch;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int seconds;
    boolean running;
    boolean wasRunning;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("x");
            running=savedInstanceState.getBoolean("y");
        }
        setContentView(R.layout.activity_main);
        runTimer();
    }
    @Override
    protected void onSaveInstanceState(Bundle sis)
    {
        super.onSaveInstanceState(sis);
        sis.putInt("x",seconds);
        sis.putBoolean("y",running);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        running=false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        running=true;
    }
    public void runTimer()
    {
        final TextView t1=findViewById(R.id.t1);
        final Handler h=new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int min=(seconds%3600)/60;
                int sec=seconds%60;
                String s=String.format("%2d:%2d:%2d",hours,min,sec);
                t1.setText(s);
                if(running)
                {
                    seconds++;
                }
                h.postDelayed(this,1000);
            }
        });

    }

    public void onClickStart(View v)
    {
        running=true;
    }
    public void onClickStop(View v)
    {
        running=false;
    }
    public void onClickReset(View v)
    {
        running=false;
        seconds=0;
    }
}
