package com.example.localmusicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.localmusicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final int STORAGE_PERMISSION_CODE = 1;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRuntimePermission();
        setTheme(R.style.coolPinkNav);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Navigation drawer
        toggle = new ActionBarDrawerToggle(this, binding.getRoot(), R.string.open, R.string.close);
        binding.getRoot().addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.shuffleBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
            startActivity(intent);
        });

        binding.favoriteBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });

        binding.playListBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);
            startActivity(intent);
        });

        binding.navView.setNavigationItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.navFeedback:
                    Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navSettings:
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navAbout:
                    Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.navExit:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            return false;
        });

    }

    private boolean requestRuntimePermission(){
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "already granted permission!", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, MainActivity.STORAGE_PERMISSION_CODE);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
//                initializeMusics();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{
                        android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}