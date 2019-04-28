package com.jackting.keepalivedemo.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jackting.keepalivedemo.service.StickyService;

/**
 * 接通一些系统广播从而拉活应用
 * 1. 8.0前，在测试过程中发现，进程在被 force stop 后，一段时间内是收不到广播的，受限较大
 * 2. 8.0后取消了大部分静态注册的广播
 * 3. 综上，低版本有一定效果，高版本效果不大，但从支付包manifest文件中来看使用了该方式配合其他方式使用
 */
public class NormalSystemKeepAliveReceiver extends BroadcastReceiver{
    private static final String TAG = "NormalSystemKeepAliveReceiver";

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if(action!=null){
            Log.i(TAG,""+action);
        }
        context.startService(new Intent(context, StickyService.class));
    }
}
