package com.example.xueyuanzhang.player.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xueyuanzhang on 16/7/23.
 */
public class PlayerControlService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("PLAY_SERVICE","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("PLAY_SERVICE","onStart");
        return START_STICKY;
    }

    public class serviceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
