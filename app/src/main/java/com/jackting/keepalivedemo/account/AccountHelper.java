package com.jackting.keepalivedemo.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class AccountHelper {

    private static final String TAG="AccountHelper";

    private static final String ACCOUNT_TYPE="com.xx.daemon.account";

    public static void addAccount(Context context){
        AccountManager accountManager=(AccountManager)context.getSystemService(Context.ACCOUNT_SERVICE);

        Account[] accounts=accountManager.getAccountsByType(ACCOUNT_TYPE);
        if(accounts.length>0){
            Log.d(TAG,"账户已存在");
            return;
        }
        Account account=new Account("xx",ACCOUNT_TYPE);

        accountManager.addAccountExplicitly(account,"xx",new Bundle());
    }

    public static void autoSync(){
        Account account=new Account("xx",ACCOUNT_TYPE);

        ContentResolver.setIsSyncable(account,"com.xx.daemon.provider",1);

        ContentResolver.setSyncAutomatically(account,"com.xx.daemon.provider",true);

        ContentResolver.addPeriodicSync(account,"com.xx.daemon.provider",new Bundle(),1);
    }
}
