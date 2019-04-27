package com.jackting.keepalivedemo.onepixel;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.jackting.keepalivedemo.KeepManager;

public class OnePixelActivity extends Activity{
    private static final String TAG = "OnePixelActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"启动 OnePixelActivity ");

        Window window=getWindow();
        window.setGravity(Gravity.START|Gravity.TOP);

        WindowManager.LayoutParams params=window.getAttributes();
        params.width = 1;
        params.height = 1;
        params.x = 0;
        params.y = 0;
        window.setAttributes(params);

        KeepManager.getInstance().setOnePixelActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"关闭 OnePixelActivity ");
    }
}
