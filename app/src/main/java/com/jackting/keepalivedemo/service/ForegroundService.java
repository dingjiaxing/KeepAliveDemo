package com.jackting.keepalivedemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ForegroundService extends Service {
    private static final String TAG = "ForegroundService";

    private static int SERVICE_ID=137890;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG,"ForegroundService onCreate");

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN_MR2){
            //4.3以下
            startForeground(SERVICE_ID,new Notification());
        }else if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            //4.3 -> 7.0
            startForeground(SERVICE_ID,new Notification());
            startService(new Intent(this,InnerService.class));
        }else {
            //8.0以上
            NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel=new NotificationChannel("channel","xx",NotificationManager.IMPORTANCE_NONE);
            if(manager==null){
                manager.createNotificationChannel(channel);
                Notification notification=new NotificationCompat.Builder(this,"channel").build();
                startForeground(SERVICE_ID,notification);
            }
        }
    }

    public static class InnerService extends Service{

        @Override
        public void onCreate() {
            super.onCreate();
            Log.d(TAG,"InnerService onCreate");
            startForeground(SERVICE_ID,new Notification());
            stopSelf();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
