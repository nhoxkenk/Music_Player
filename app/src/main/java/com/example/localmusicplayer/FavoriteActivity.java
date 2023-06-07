package com.example.localmusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.localmusicplayer.databinding.ActivityFavoriteBinding;

public class FavoriteActivity extends AppCompatActivity {
    private ActivityFavoriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}