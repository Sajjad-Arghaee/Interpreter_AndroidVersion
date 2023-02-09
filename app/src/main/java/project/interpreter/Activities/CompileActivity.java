package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.interpreter.Core.Float;
import project.interpreter.Core.Int;
import project.interpreter.R;
import project.interpreter.Core.Reader;

public class CompileActivity extends AppCompatActivity {

    EditText writing;
    TextView show;
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
        setContentView(R.layout.activity_compile);
        writing = findViewById(R.id.writing);
        show = findViewById(R.id.show);

    }

    public void compiler(View view) {
        mp.start();
        if(view.getId() == R.id.compileFile){
            String data = writing.getText().toString();
            try {
                StringBuilder result = Reader.read(data);
                show.setText(result.toString());
                Reader.stream.delete(0, Reader.stream.length());
                Reader.intData = new Int();
                Reader.floatData = new Float();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(view.getId() == R.id.chooseFile){
            Toast.makeText(this, "not available", Toast.LENGTH_SHORT).show();
        }
    }


}