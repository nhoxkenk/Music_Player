package com.example.localmusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.localmusicplayer.databinding.ActivityPlayerBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerActivity extends AppCompatActivity {
    private ActivityPlayerBinding binding;
    public static ArrayList<Music> musicListPA = new ArrayList<>();
    public static int songPosition = 0;
    public static MediaPlayer mediaPlayer;
    public static boolean isPLaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeLayout();

        binding.playPauseBtn.setOnClickListener(view -> {
            if(isPLaying) pauseMusic();
            else playMusic();
        });
        binding.previousBtnPA.setOnClickListener(view -> {
            prevNextSong(false);
        });
        binding.nextBtnPA.setOnClickListener(view -> {
            prevNextSong(true);
        });
    }

    private void setLayout(){
        Glide.with(this)
                .load(musicListPA.get(songPosition).getArtUri())
                .apply(RequestOptions.placeholderOf(R.drawable.music_splash_screen).centerCrop())
                .into(binding.songImgPA);
        binding.songNamePA.setText(musicListPA.get(songPosition).getTitle());
    }

    private void createMediaPlayer(){
        if(mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(musicListPA.get(songPosition).getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.start();
        isPLaying = true;
        binding.playPauseBtn.setIconResource(R.drawable.pause_icon);
    }

    private void initializeLayout(){
        Intent intent = getIntent();
        songPosition = intent.getIntExtra("index", 0);
        switch (intent.getStringExtra("class")){
            case "MusicAdapter":
                musicListPA.addAll(MainActivity.musics);
                setLayout();
                createMediaPlayer();
                break;

            case "MainActivity":
                musicListPA.addAll(MainActivity.musics);
                Collections.shuffle(musicListPA);
                setLayout();
                createMediaPlayer();
                break;
            default:
                break;
        }
    }

    private void pauseMusic(){
        isPLaying = false;
        mediaPlayer.pause();
        //musicService.mediaPlayer.pause();
        //musicService.showNotification(R.drawable.play_icon);
        binding.playPauseBtn.setIconResource(R.drawable.play_icon);
    }

    private void playMusic(){
        isPLaying = true;
        mediaPlayer.start();
        //musicService.mediaPlayer.start();
        //musicService.showNotification(R.drawable.pause_icon);
        binding.playPauseBtn.setIconResource(R.drawable.pause_icon);
    }

    private void prevNextSong(boolean increment){
        if(increment){
            Music.setSongPosition(true); // fix lại vị trí của từng bài hát khi out of range của arrayList với đầu vào boolean.
        }
        else {
            Music.setSongPosition(false);
        }
        setLayout(); // hàm thực hiện việc load ảnh và tên của từng bài hát ra layoyt player
        createMediaPlayer(); // tạo mới 1 mediaPlayer với path của bài hát đã được lấy ra
    }
}