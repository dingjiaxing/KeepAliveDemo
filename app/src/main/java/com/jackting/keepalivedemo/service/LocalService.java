package com.jackting.keepalivedemo.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import aidl.IRemoteConnection;

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    ServiceConnection serviceConnection;
    MyBinder binder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(binder==null){
            binder=new MyBinder();
        }
        serviceConnection=new MyServiceConnection();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(LocalService.this,RemoteService.class),
                serviceConnection,BIND_AUTO_CREATE);
        return START_STICKY;
    }

    class MyBinder extends IRemoteConnection.Stub{

        @Override
        public String getProcessName() throws RemoteException {
            return "LocalService";
        }
    }

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,"remote service 可能被杀死了，拉活");

            startService(new Intent(LocalService.this,RemoteService.class));
            bindService(new Intent(LocalService.this,RemoteService.class),
                    serviceConnection,BIND_AUTO_CREATE);
        }
    }

    public static class InnerService extends Service{

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            startForeground(16,new Notification());
            stopSelf();
        }
    }
}
