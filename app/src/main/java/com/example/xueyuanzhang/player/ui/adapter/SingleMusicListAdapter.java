package com.example.xueyuanzhang.player.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xueyuanzhang.player.R;
import com.example.xueyuanzhang.player.model.Songs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xueyuanzhang on 16/7/24.
 */
public class SingleMusicListAdapter extends RecyclerView.Adapter{
    private List<Songs> list;
    private Context context;
    private OnPlayBarClickListener onPlayBarClickListener;

    public interface OnPlayBarClickListener{
        void onPlay(int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.song_name)
        TextView nameTV;
        @BindView(R.id.song_composer)
        TextView composerTV;
        @BindView(R.id.song_album)
        TextView albumTV;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onPlayBarClickListener!=null){
                onPlayBarClickListener.onPlay(getAdapterPosition());
            }
        }
    }

    public void setOnPlayBarClickListener(OnPlayBarClickListener onPlayBarClickListener) {
        this.onPlayBarClickListener = onPlayBarClickListener;
    }

    public SingleMusicListAdapter(Context context, List<Songs> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_music,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder  = (ItemViewHolder) holder;
        Songs song = list.get(position);
        viewHolder.nameTV.setText(song.getName());
        viewHolder.composerTV.setText(song.getComposer());
        viewHolder.albumTV.setText(song.getAlbum());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
