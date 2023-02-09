package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import project.interpreter.R;

public class IntroActivity extends AppCompatActivity {
    private int check = 0;
    ImageButton start;
    ImageButton next;
    ImageView world;
    ImageView enjoy;
    ImageView stepByStep;
    MediaPlayer mp;
    private MediaPlayer mediaPlayer;

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
        setContentView(R.layout.activity_intro);
        mp = MediaPlayer.create(this, R.raw.select);
        stepByStep = findViewById(R.id.stepbystep);
        world = findViewById(R.id.world);
        enjoy = findViewById(R.id.enjoy);
        start = findViewById(R.id.start);
        next = findViewById(R.id.next);
        world.setTranslationX(1505);
        enjoy.setTranslationX(1505);
        start.setTranslationY(500);
        mediaPlayer = MediaPlayer.create(this, R.raw.intro);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void start(View view) {
        mp.start();
        if (view.getId() == R.id.next) {
            switch (check) {
                case 0:
                    world.animate().translationXBy(-1505).setDuration(1500);
                    stepByStep.animate().translationXBy(-1505).alpha(0).setDuration(1500);
                    break;
                case 1:
                    enjoy.animate().translationXBy(-1505).setDuration(1500);
                    world.animate().translationXBy(-1505).alpha(0).setDuration(1500);
                    next.animate().translationYBy(-1505).alpha(0).setDuration(1500);
                    start.animate().translationYBy(-500);
                default:
                    break;
            }
            check++;
            if (check == 2) {
                next.setTranslationY(-1505);
                start.setTranslationY(-500);
            }
        }
        if (view.getId() == R.id.start) {
            Intent intent = new Intent(IntroActivity.this, MenuActivity.class);
            mediaPlayer.stop();
            finish();
            startActivity(intent);
        }
    }
}