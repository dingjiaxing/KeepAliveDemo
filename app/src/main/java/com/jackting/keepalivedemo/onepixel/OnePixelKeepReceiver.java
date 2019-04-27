package com.jackting.keepalivedemo.onepixel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jackting.keepalivedemo.KeepManager;

public class OnePixelKeepReceiver extends BroadcastReceiver {
    private static final String TAG = "OnePixelKeepReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.d(TAG,"onReceive "+action);
        if(action!=null){
            if(action.equals(Intent.ACTION_SCREEN_OFF)){
                //屏幕关闭，打开1像素activity
                KeepManager.getInstance().startOnePixel(context);
            }else if(action.equals(Intent.ACTION_SCREEN_ON)){
                //屏幕打开，关闭1像素activity
                KeepManager.getInstance().finishOnePixel();
            }
        }
    }
}
