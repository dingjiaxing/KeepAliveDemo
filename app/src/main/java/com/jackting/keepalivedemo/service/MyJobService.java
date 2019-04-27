package com.jackting.keepalivedemo.service;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private static String TAG="MyJobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"onStartJob");

        //7.0以上轮训
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            startJob(this);
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public static void startJob(Context context){
        JobScheduler jobScheduler= (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        JobInfo.Builder builder=new JobInfo.Builder(8,new ComponentName(context.getPackageName(),MyJobService.class.getName())
                ).setPersisted(true);

        //小于 7.0
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            //每隔 1s 执行一次 job
            builder.setPeriodic(1000);
        }else {
            //延迟执行任务
            builder.setMinimumLatency(1000);
        }
        jobScheduler.schedule(builder.build());
    }
}
