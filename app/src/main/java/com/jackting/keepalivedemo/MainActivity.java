package com.jackting.keepalivedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jackting.keepalivedemo.account.AccountHelper;
import com.jackting.keepalivedemo.service.ForegroundService;
import com.jackting.keepalivedemo.service.LocalService;
import com.jackting.keepalivedemo.service.MyJobService;
import com.jackting.keepalivedemo.service.RemoteService;

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
//        MyJobService.startJob(this);

        //双进程拉活
//        startService(new Intent(this, LocalService.class));
//        startService(new Intent(this, RemoteService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //1像素保活，反注册
        KeepManager.getInstance().unregisterOnePixel(this);
    }
}
