package com.jackting.keepalivedemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.jackting.keepalivedemo.onepixel.OnePixelActivity;
import com.jackting.keepalivedemo.onepixel.OnePixelKeepReceiver;

import java.lang.ref.WeakReference;

public class KeepManager {
    WeakReference<Activity> onePixelActivity;
    OnePixelKeepReceiver onePixelKeepReceiver;

    private static class SingleHolder{
        private static final KeepManager keepManager=new KeepManager();
    }

    public static KeepManager getInstance(){
        return SingleHolder.keepManager;
    }

    public void registerOnePixel(Context context){
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        onePixelKeepReceiver=new OnePixelKeepReceiver();
        context.registerReceiver(onePixelKeepReceiver,filter);
    }

    public void unregisterOnePixel(Context context){
        if(onePixelKeepReceiver!=null){
            context.unregisterReceiver(onePixelKeepReceiver);
        }
    }

    public void startOnePixel(Context context){
        Intent intent= new Intent(context, OnePixelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void finishOnePixel(){
        if(onePixelActivity!=null){
            Activity activity=onePixelActivity.get();
            if(activity!=null){
                activity.finish();
            }
            onePixelActivity=null;
        }
    }

    public void setOnePixelActivity(OnePixelActivity pixelActivity){
        this.onePixelActivity=new WeakReference<Activity>(pixelActivity);
    }
}
