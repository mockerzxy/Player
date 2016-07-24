package com.example.xueyuanzhang.player.ui.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xueyuanzhang.player.R;
import com.example.xueyuanzhang.player.model.Songs;
import com.example.xueyuanzhang.player.ui.adapter.OverallViewPagerAdapter;
import com.example.xueyuanzhang.player.ui.fragment.MusicFragment;
import com.example.xueyuanzhang.player.ui.fragment.RecordFragment;
import com.example.xueyuanzhang.player.ui.fragment.SocialFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private OverallViewPagerAdapter fragmentAdapter;
    private MusicFragment musicFragment;
    private RecordFragment recordFragment;
    private SocialFragment socialFragment;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

        }
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        ButterKnife.bind(this);
        initDrawer();
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        initViewPager();
    }

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        musicFragment = new MusicFragment();
        recordFragment = new RecordFragment();
        socialFragment = new SocialFragment();
        fragmentList.add(musicFragment);
        fragmentList.add(recordFragment);
        fragmentList.add(socialFragment);
        fragmentAdapter = new OverallViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }





}
