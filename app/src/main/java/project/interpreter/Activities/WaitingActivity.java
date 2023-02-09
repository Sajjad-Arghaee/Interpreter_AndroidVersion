package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.Timer;
import java.util.TimerTask;

import project.interpreter.R;

public class WaitingActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_waiting);
        ProgressBar progressBar = findViewById(R.id.progress);
        Sprite threeBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(threeBounce);
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                String result = LoginActivity.getContentOfUrlConnection("https://sajjad8080.000webhostapp.com/search.php?myID=" + LoginActivity.myID);
				if(!result.equals("wait")){
					LoginActivity.opponentID = Integer.parseInt(result);
                    Intent intent = new Intent(WaitingActivity.this, OnlineActivity.class);
                    startActivity(intent);
                    finish();
                    t.cancel();
                }
            }
        }, 2000, 1000);
    }
    @Override
    protected void onResume() {
        //stop service and stop music
        super.onResume();
        MenuActivity.mediaPlayer.start();
    }
}