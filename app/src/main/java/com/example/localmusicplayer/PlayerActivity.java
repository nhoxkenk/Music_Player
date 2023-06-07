package com.example.localmusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.localmusicplayer.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {
    private ActivityPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}