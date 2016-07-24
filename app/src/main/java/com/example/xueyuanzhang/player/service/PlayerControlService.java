package com.example.xueyuanzhang.player.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.xueyuanzhang.player.model.BroadcastAuthorInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueyuanzhang on 16/7/23.
 */
public class PlayerControlService extends Service{
    private MediaPlayer mediaPlayer;
    private ServiceReceiver serviceReceiver;
    private int current;
    private ArrayList<String> songPathList;
    private AssetManager assetManager;
    private int status;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("PLAY_SERVICE","onCreate");
        assetManager = getAssets();
        registerBroadcast();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                if(current>songPathList.size()-1){
                    current=0;
                }
                playMusic(songPathList.get(current));
            }
        });
    }

    private void registerBroadcast(){
        serviceReceiver = new ServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BroadcastAuthorInfo.PLAY_CONTROL);
        registerReceiver(serviceReceiver,filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("PLAY_SERVICE","onStart");
        return START_STICKY;
    }

    public class ServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            songPathList = intent.getStringArrayListExtra("SONG_LIST");
            current = intent.getIntExtra("POSITION",-1);
            int control = intent.getIntExtra("STATUS",-1);
//1 play  2 pause
            switch (control){
                case 0:
                    playMusic(songPathList.get(current));
                    status = 1;
                    break;
                case 1:
                    mediaPlayer.pause();
                    status = 2;
                    break;
                case 2:
                    mediaPlayer.start();
                    status = 1;
                    break;
                case 3:
                    mediaPlayer.reset();
                    status = 2;
                    break;
            }
            Intent intent1 = new Intent(BroadcastAuthorInfo.STATUS_FEED_BACK);
            intent1.putExtra("STATUS",status);
            sendBroadcast(intent1);

        }
    }

    private void playMusic(String path){

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceReceiver);
    }
}
