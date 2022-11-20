package com.dodo.kanbagis.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class AdvertReceiver extends BroadcastReceiver {
    private static final String TAG = "AdvertReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
