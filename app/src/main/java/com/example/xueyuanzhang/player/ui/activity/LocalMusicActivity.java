package com.example.xueyuanzhang.player.ui.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.xueyuanzhang.player.R;
import com.example.xueyuanzhang.player.model.Songs;
import com.example.xueyuanzhang.player.ui.adapter.LocalMusicAdapter;
import com.example.xueyuanzhang.player.ui.fragment.AlbumFragment;
import com.example.xueyuanzhang.player.ui.fragment.SingerFragment;
import com.example.xueyuanzhang.player.ui.fragment.SingleMusicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xueyuanzhang on 16/7/24.
 */
public class LocalMusicActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<Songs> localMusicPathList;
    private List<Fragment> fragmentList;
    private SingleMusicFragment singleMusicFragment;
    private SingerFragment singerFragment;
    private AlbumFragment albumFragment;
    private LocalMusicAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        initView();


    }

    private void initView() {
        ButterKnife.bind(this);
        initToolbar();
        getLocalMusic();
        initViewPager();
    }

    private void initToolbar() {
        toolbar.setTitle(getString(R.string.localMusic));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getLocalMusic() {
        localMusicPathList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DISPLAY_NAME);
        while (cursor.moveToNext()) {
            Songs song = new Songs();
            song.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            song.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            song.setComposer(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            song.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
            if(!"<unknown>".equals(song.getComposer())) {
                localMusicPathList.add(song);
            }

        }
        cursor.close();

    }

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        List<String> title = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("SONG_LIST", localMusicPathList);
        singleMusicFragment = new SingleMusicFragment();
        singerFragment = new SingerFragment();
        albumFragment = new AlbumFragment();
        singleMusicFragment.setArguments(bundle);
        singerFragment.setArguments(bundle);
        albumFragment.setArguments(bundle);
        fragmentList.add(singleMusicFragment);
        fragmentList.add(singerFragment);
        fragmentList.add(albumFragment);
        title.add(getString(R.string.singleMusic));
        title.add(getString(R.string.singer));
        title.add(getString(R.string.album));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        pagerAdapter = new LocalMusicAdapter(getSupportFragmentManager(), fragmentList, title);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
