package com.example.xueyuanzhang.player.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xueyuanzhang.player.R;
import com.example.xueyuanzhang.player.model.Songs;
import com.example.xueyuanzhang.player.service.PlayerControlService;
import com.example.xueyuanzhang.player.ui.adapter.SingleMusicListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xueyuanzhang on 16/7/24.
 */
public class SingleMusicFragment extends Fragment{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<Songs> songList;
    private SingleMusicListAdapter listAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null) {
            songList = (ArrayList<Songs>) getArguments().getSerializable("SONG_LIST");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_music,container,false);
        ButterKnife.bind(this,view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        listAdapter = new SingleMusicListAdapter(getActivity().getApplicationContext(),songList);
        //播放音乐
        listAdapter.setOnPlayBarClickListener(new SingleMusicListAdapter.OnPlayBarClickListener() {
            @Override
            public void onPlay(int position) {
//                Intent intent = new Intent(getActivity(), PlayerControlService.class);
//                getActivity().startService(intent);
                Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(listAdapter);


    }


}
