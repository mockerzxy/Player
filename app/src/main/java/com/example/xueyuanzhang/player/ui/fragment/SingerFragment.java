package com.example.xueyuanzhang.player.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xueyuanzhang.player.R;
import com.example.xueyuanzhang.player.model.Songs;

import java.util.ArrayList;

/**
 * Created by xueyuanzhang on 16/7/24.
 */
public class SingerFragment extends Fragment{
    ArrayList<Songs> songList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null) {
            songList = (ArrayList<Songs>) savedInstanceState.getSerializable("SONG_LIST");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singer,container,false);
        return view;    }
}
