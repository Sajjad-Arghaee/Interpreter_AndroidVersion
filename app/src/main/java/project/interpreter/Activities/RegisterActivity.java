package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import project.interpreter.R;

import static project.interpreter.Activities.LoginActivity.getContentOfUrlConnection;

public class RegisterActivity extends AppCompatActivity {

	EditText username;
	EditText password;
	EditText email;
	TextView textView;
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
        setContentView(R.layout.activity_register);
		username = findViewById(R.id.username);
		password = findViewById(R.id.password);
		email = findViewById(R.id.email);
		textView = findViewById(R.id.textView);
		mp = MediaPlayer.create(this, R.raw.select);
    }

    public void signUP(View view) {
		mp.start();
		if(view.getId() == R.id.signUp){
			final Handler handler = new Handler();
			Thread thread = new Thread(() -> {
				String result = getContentOfUrlConnection("https://sajjad8080.000webhostapp.com/register.php?username=" + username.getText().toString()
			+ "&password=" + password.getText().toString() + "&email=" + email.getText().toString());

            if (result.contains("registered before")) {
            }else {
				Intent intent = new Intent(RegisterActivity.this, SuccessActivity.class);
				finish();
				startActivity(intent);
			}

            handler.post(() -> {

            });
        });
        thread.start();
		}
    }
}