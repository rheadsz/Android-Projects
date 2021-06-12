package com.rhea.backgroundapp;




import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Wallpaper extends Service {
    WallpaperManager wpm;
    Timer myTimer;
    Drawable drawable;
    int num=1;

    @Override
    public void onCreate() {
        super.onCreate();
        myTimer=new Timer();
        wpm=WallpaperManager.getInstance(Wallpaper.this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(num==1)
                {
                    drawable=getResources().getDrawable(R.drawable.one);
                    num=2;
                }
                else if(num==2)
                {
                    drawable=getResources().getDrawable(R.drawable.two);
                    num=3;
                }
                else if(num==3)
                {
                    drawable=getResources().getDrawable(R.drawable.three);
                    num=4;
                }
                else if(num==4)
                {
                    drawable=getResources().getDrawable(R.drawable.four);
                    num=5;
                }
                else
                {
                    drawable=getResources().getDrawable(R.drawable.five);
                    num=1;
                }

                Bitmap wallpaper = ((BitmapDrawable)drawable).getBitmap();
                try{
                    wpm.setBitmap(backgroundapp);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        },0, 2000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}