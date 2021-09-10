package com.mobdeve.project.sibat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;


import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private Button restartBtn, quitBtn;
    private GameView gameView;
    private MediaPlayer mediaPlayer;
    private ImageButton chara1, chara2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Constants.CHARAIMG = 1;





        this.sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.spEditor = this.sp.edit();

        Constants.EDITOR = this.spEditor;
        Constants.SP = this.sp;

        Constants.HIGHSCORE = sp.getInt("HighScore", 0);


        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;
        setContentView(R.layout.activity_main);
        Constants.PAUSEVIEW = (TextView) this.findViewById(R.id.pause_tv);
        Constants.SCOREVIEW = (TextView) this.findViewById(R.id.scorenum_tv);
        Constants.SCORETEXT = (TextView) this.findViewById(R.id.score_tv);
        Constants.INSTRUCTIONS = (TextView) this.findViewById(R.id.instructions_tv);
        Constants.HIGHSCORETEXT = (TextView) this.findViewById(R.id.highscoretext_tv);
        Constants.RESTART = (Button) this.findViewById(R.id.restart_btn);
        Constants.QUIT = (Button) this.findViewById(R.id.quit_btn);
        Constants.INITIAL = (TextView) this.findViewById(R.id.initialScreen_tv);
        Constants.CHOOSE = (TextView) this.findViewById(R.id.ChooseScreen_tv);

        Constants.CHARA1 = (ImageButton) this.findViewById(R.id.chara1_ib);
        Constants.CHARA2 = (ImageButton) this.findViewById(R.id.chara2_ib);

        restartBtn = this.findViewById(R.id.restart_btn);
        gameView = this.findViewById(R.id.gameView);
        quitBtn = this.findViewById(R.id.quit_btn);



        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        mediaPlayer.setVolume((float)0.05, (float)0.05);

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.INITIAL.setVisibility(View.VISIBLE);
                Constants.CHOOSE.setVisibility(View.VISIBLE);
                Constants.CHARA1.setVisibility(View.VISIBLE);
                Constants.CHARA2.setVisibility(View.VISIBLE);
                Constants.SCOREVIEW.setVisibility(View.GONE);
                Constants.SCORETEXT.setVisibility(View.GONE);
                gameView.reset();
            }
        });


        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        Constants.CHARA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Constants.CHARAIMG = 1;
                gameView.reset();
            }
        });

        Constants.CHARA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Constants.CHARAIMG = 2;
                gameView.reset();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        spEditor.apply();
    }
}