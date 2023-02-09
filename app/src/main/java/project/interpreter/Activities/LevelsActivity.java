package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import project.interpreter.R;

public class LevelsActivity extends AppCompatActivity {
    public static int stage = 1;
    MediaPlayer mp;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp = MediaPlayer.create(this, R.raw.select);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_levels);
    }

    public void imageListener(View view) {
        mp.start();

        if(view.getId() == R.id.stage1){
            stage = 1;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage2){
            stage = 2;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage3){
            stage = 3;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage4){
            stage = 4;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage5){
            stage = 5;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage6){
            stage = 6;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage7){
            stage = 7;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage8){
            stage = 8;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.stage9){
            stage = 9;
            Intent intent = new Intent(LevelsActivity.this, QuestionActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.back)
            this.finish();
    }
    @Override
    protected void onResume() {
        //stop service and stop music
        super.onResume();
        MenuActivity.mediaPlayer.start();
    }
}