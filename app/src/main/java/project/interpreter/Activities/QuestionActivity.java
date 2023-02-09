package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import project.interpreter.Core.Float;
import project.interpreter.Core.Int;
import project.interpreter.R;
import project.interpreter.Core.Reader;

public class QuestionActivity extends AppCompatActivity {
    private int lineCounter = 0;
    EditText editText;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView output;
    ConstraintLayout layout;
    MediaPlayer mp;
    public MediaPlayer mediaPlayer2;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp = MediaPlayer.create(this, R.raw.select);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.stages);
        MenuActivity.mediaPlayer.pause();
        mediaPlayer2.setLooping(true);
        mediaPlayer2.start();
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_question);
        layout = findViewById(R.id.layout);
        switch (LevelsActivity.stage) {
            case 1:
                layout.setBackgroundResource(R.drawable.question1);
                break;
            case 2:
                layout.setBackgroundResource(R.drawable.question2);
                break;
            case 3:
                layout.setBackgroundResource(R.drawable.question3);
                break;
            case 4:
                layout.setBackgroundResource(R.drawable.question4);
                break;
            case 5:
                layout.setBackgroundResource(R.drawable.question5);
                break;
            case 6:
                layout.setBackgroundResource(R.drawable.question6);
                break;
            case 7:
                layout.setBackgroundResource(R.drawable.question7);
                break;
            case 8:
                layout.setBackgroundResource(R.drawable.question8);
                break;
            case 9:
                layout.setBackgroundResource(R.drawable.question9);
            default:
                break;
        }
        editText = findViewById(R.id.editText);
        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv3);
        textView4 = findViewById(R.id.tv4);
        textView5 = findViewById(R.id.tv5);
        textView6 = findViewById(R.id.tv6);
        textView7 = findViewById(R.id.tv7);
        textView8 = findViewById(R.id.tv8);
        textView9 = findViewById(R.id.tv9);
        textView10 = findViewById(R.id.tv10);
        output = findViewById(R.id.output);
    }

    public void imageListener(View view) {
        mp.start();
        if (view.getId() == R.id.send) {
            switch (lineCounter) {
                case 0:
                    textView1.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 1:
                    textView2.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 2:
                    textView3.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 3:
                    textView4.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 4:
                    textView5.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 5:
                    textView6.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 6:
                    textView7.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 7:
                    textView8.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 8:
                    textView9.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                    break;
                case 9:
                    textView10.setText(editText.getText());
                    editText.setText("");
                    lineCounter++;
                default:
                    break;
            }
        }
        if (view.getId() == R.id.run) {
            String data = textView1.getText() + "\n" +
                    textView2.getText() + "\n" +
                    textView3.getText() + "\n" +
                    textView4.getText() + "\n" +
                    textView5.getText() + "\n" +
                    textView6.getText() + "\n" +
                    textView7.getText() + "\n" +
                    textView8.getText() + "\n" +
                    textView9.getText() + "\n" +
                    textView10.getText();
            try {
                StringBuilder result = Reader.read(data);
                output.setText(result != null ? result.toString() : null);
                Reader.stream.delete(0, Reader.stream.length());
                Reader.intData = new Int();
                Reader.floatData = new Float();
                Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.video_showing);
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                lp.copyFrom(dialog.getWindow().getAttributes());
                dialog.getWindow().setAttributes(lp);
                final VideoView videoView = dialog.findViewById(R.id.video);
                Uri uri;
                if (result != null && result.toString().equals(CorrectAnswers.getAnswer(LevelsActivity.stage)))
                    uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.correct);
                else
                    uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wrong);
                videoView.setVideoURI(uri);
                mediaPlayer2.pause();
                videoView.start();
                dialog.setOnDismissListener(arg -> mediaPlayer2.start());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (view.getId() == R.id.reset) {
            lineCounter = 0;
            textView1.setText(null);
            textView2.setText(null);
            textView3.setText(null);
            textView4.setText(null);
            textView5.setText(null);
            textView6.setText(null);
            textView7.setText(null);
            textView8.setText(null);
            textView9.setText(null);
            textView10.setText(null);
        }
    }

    @Override
    protected void onPause() {
        //stop service and stop music
        super.onPause();
        mediaPlayer2.stop();
        MenuActivity.mediaPlayer.start();
        lineCounter = 0;
    }
    @Override
    protected void onStop() {
        super.onStop();
        lineCounter = 0;
    }
}