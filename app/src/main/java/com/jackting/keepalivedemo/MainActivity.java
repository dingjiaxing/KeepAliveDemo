package com.jackting.keepalivedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jackting.keepalivedemo.account.AccountHelper;
import com.jackting.keepalivedemo.service.ForegroundService;
import com.jackting.keepalivedemo.service.MyJobService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1像素保活，仅可提高进程优先级，仅能保活，不能拉活
//        KeepManager.getInstance().registerOnePixel(this);

        //前台服务保活，仅可提高进程优先级，仅能保活，不能拉活
//        startService(new Intent(this,ForegroundService.class));

        //sticky拉活
//        startService(new Intent(this,StickyService.class));

        //账户同步拉活
//        AccountHelper.addAccount(this);
//        AccountHelper.autoSync();

        //JobScheduler拉活
        MyJobService.startJob(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //1像素保活，反注册
        KeepManager.getInstance().unregisterOnePixel(this);
    }
}
