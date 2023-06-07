package com.example.localmusicplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.localmusicplayer.databinding.MusicViewBinding;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyHolder>{

    private Context context;

    private ArrayList<Music> musics = new ArrayList<>();

    public MusicAdapter(Context context, ArrayList<Music> musics) {
        this.context = context;
        this.musics = musics;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MusicViewBinding musicViewBinding = MusicViewBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new MyHolder(musicViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.title.setText(musics.get(position).getTitle());
        holder.artist.setText(musics.get(position).getArtist());
        holder.duration.setText(musics.get(position).formatDuration(musics.get(position).getDuration()));
        Glide.with(context)
                .load(musics.get(position).getArtUri())
                .apply(RequestOptions.placeholderOf(R.drawable.splash_screen).centerCrop())
                .into(holder.image);
        holder.root.setOnClickListener(view -> {
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra("index",holder.getAdapterPosition());
            intent.putExtra("class", "MusicAdapter");
            ContextCompat.startActivity(context, intent, null);
        });
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        MusicViewBinding binding;
        RelativeLayout root;
        TextView title;
        TextView artist;
        ImageView image;
        TextView duration;
        public MyHolder(@NonNull MusicViewBinding musicViewBinding) {
            super(musicViewBinding.getRoot());
            this.binding = musicViewBinding;
            title = binding.songName;
            artist = binding.songArtist;
            image = binding.imageMV;
            duration = binding.songDuration;
            root = binding.getRoot();
        }
    }
}
