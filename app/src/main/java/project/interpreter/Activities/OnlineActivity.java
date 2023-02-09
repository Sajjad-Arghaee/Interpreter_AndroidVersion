package project.interpreter.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

import project.interpreter.Core.Float;
import project.interpreter.Core.Int;
import project.interpreter.R;
import project.interpreter.Core.Reader;

import static project.interpreter.Activities.LoginActivity.getContentOfUrlConnection;

public class OnlineActivity extends AppCompatActivity {
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
    TextView chat;
    EditText chatText;
    ImageButton sendChat;
    ConstraintLayout layout;
    ImageButton run;
    private boolean falseDialog = false;
    MediaPlayer mp;
    MediaPlayer mediaPlayer2;
    boolean ok = false;
    boolean chatting = false;
    String result;
    int state;
    static int received = 0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp = MediaPlayer.create(this, R.raw.select);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.challenge);
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
        setContentView(R.layout.activity_online);

        layout = findViewById(R.id.layout);
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
        run = findViewById(R.id.run);
        chatText = findViewById(R.id.chattext);
        sendChat = findViewById(R.id.chatsend);
        chat = findViewById(R.id.chat);
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (chatting) {
                    LoginActivity.getContentOfUrlConnection("https://sajjad8080.000webhostapp.com/chat.php?myID=" + LoginActivity.myID + "&chat=" + chatText.getText().toString());
                    chatText.setText(null);
                    chatting = false;
                }
                result = LoginActivity.getContentOfUrlConnection("https://sajjad8080.000webhostapp.com/check.php?myID=" + LoginActivity.myID + "&opponentID=" + LoginActivity.opponentID +
                        "&received=" + received);

                state = -1;
                if (result.length() == 1)
                    state = Integer.parseInt(result);
                if (state == 0) {
                    falseDialog = true;
                    t.cancel();
                } else if (ok)
                    getContentOfUrlConnection("https://sajjad8080.000webhostapp.com/winner.php?myID=" + LoginActivity.myID + "&opponentID=" + LoginActivity.opponentID);
            }
        }, 0, 1000);


    }

    @SuppressLint("SetTextI18n")
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
            StringBuilder result;
            try {
                result = Reader.read(data);
                output.setText(result.toString());
                Reader.stream.delete(0, Reader.stream.length());
                Reader.intData = new Int();
                Reader.floatData = new Float();
                if (result.toString().equals(CorrectAnswers.getAnswer(1))) {
                    ok = true;
                    playDialog(true);
                } else
                    playDialog(false);
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

        if (view.getId() == R.id.chatsend && chatText.getText().toString().length() > 0) {
            chat.append("me: " + chatText.getText().append("\n"));
            chatting = true;
        }

        if (view.getId() == R.id.refresh) {
            if (state == 0)
                playDialog(false);
            if(received == 1)
                received = 0;
            else if (result.contains(":")) {
                chat.append(result + "\n");
                received = 1;
            }
        }
    }

    public void playDialog(boolean state) {
        try {
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
            if (state) {
                uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.correct);
            } else {
                uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wrong);
            }
            videoView.setVideoURI(uri);
            mediaPlayer2.pause();
            videoView.start();
            dialog.setOnDismissListener(arg -> {
                if (state)
                    finish();
                if (!state && falseDialog)
                    finish();
                mediaPlayer2.start();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        //stop service and stop music
        super.onStop();
        mediaPlayer2.stop();
        MenuActivity.mediaPlayer.start();
    }
}