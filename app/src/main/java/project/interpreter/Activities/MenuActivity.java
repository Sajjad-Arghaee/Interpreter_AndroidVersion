package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import project.interpreter.R;

public class MenuActivity extends AppCompatActivity {

    ImageButton levels;
    ImageButton compile;
    ImageButton online;
    ImageButton exit;
    MediaPlayer mp;
    public static MediaPlayer mediaPlayer;
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
        setContentView(R.layout.activity_menu);
        levels = findViewById(R.id.levels);
        compile = findViewById(R.id.compilebtn);
        online = findViewById(R.id.online);
        exit = findViewById(R.id.exit);

        levels.setTranslationX(-2000f);
        levels.animate().translationXBy(2000f).setDuration(1500);
        levels.animate().alpha(1f).setDuration(1500);
        compile.setTranslationX(2000f);
        compile.animate().translationXBy(-2000f).setDuration(2000);
        compile.animate().alpha(1f).setDuration(2000);
        online.setTranslationX(-2000f);
        online.animate().translationXBy(2000f).setDuration(1300);
        online.animate().alpha(1f).setDuration(1300);
        exit.setTranslationX(2000f);
        exit.animate().translationXBy(-2000f).setDuration(2300);
        exit.animate().alpha(1f).setDuration(2300);
        mp = MediaPlayer.create(this, R.raw.select);
        mediaPlayer = MediaPlayer.create(this, R.raw.back);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    public void btnListener(View view){
        mp.start();
        if(view.getId() == R.id.online){
            Intent intent = new Intent(MenuActivity.this, ManageActivity.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.levels){
            Intent intent = new Intent(MenuActivity.this, LevelsActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.compilebtn){
            Intent intent = new Intent(MenuActivity.this, CompileActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.exit){
	    mediaPlayer.stop();
            this.finish();
        }
    }

}