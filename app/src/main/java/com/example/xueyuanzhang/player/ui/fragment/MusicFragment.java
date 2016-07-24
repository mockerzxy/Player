package com.example.xueyuanzhang.player.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.xueyuanzhang.player.R;
import com.example.xueyuanzhang.player.model.Songs;
import com.example.xueyuanzhang.player.ui.activity.LocalMusicActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xueyuanzhang on 16/7/23.
 */
public class MusicFragment extends Fragment {
    @BindView(R.id.local_music_bar)
    LinearLayout localMusicBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);
        functionHandle();
        return view;
    }

    private void functionHandle() {
        localMusicBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocalMusicActivity.class);
                startActivity(intent);

            }
        });
    }


}
