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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.interpreter.R;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    public static int myID;
    public static int opponentID;
    private String result;
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
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void signIn(View view) {
        mp.start();
        if (view.getId() == R.id.signIn) {
            if (canEnter(username.getText().toString(), password.getText().toString())) {
                Intent intent;
                intent = new Intent(LoginActivity.this, WaitingActivity.class);
                startActivity(intent);
            }
        }
    }

    boolean state = false;
    private boolean canEnter(String username, String password) {
        final Handler handler = new Handler();
        Thread thread = new Thread( () -> {
            result = getContentOfUrlConnection("https://sajjad8080.000webhostapp.com/login.php?username=" + username + "&password=" + password);
            if (!result.equals("not found!")) {
				myID = Integer.parseInt(result);
				state = true;
            } 

            handler.post(() -> {

            });
        });
        thread.start();
        return state;
    }

    public static String getContentOfUrlConnection(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            return InputStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String InputStreamToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}