package com.example.localmusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.localmusicplayer.databinding.ActivityPlaylistBinding;

public class PlaylistActivity extends AppCompatActivity {
    private ActivityPlaylistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaylistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}