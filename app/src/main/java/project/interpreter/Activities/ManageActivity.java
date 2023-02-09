package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import project.interpreter.R;

public class ManageActivity extends AppCompatActivity {
    MediaPlayer mp;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_manage);
        mp = MediaPlayer.create(this, R.raw.select);
    }
    public void choose(View view) {
        mp.start();
        Intent intent;
        if(view.getId() == R.id.register){
            intent = new Intent(ManageActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.login){
            intent = new Intent(ManageActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }
}