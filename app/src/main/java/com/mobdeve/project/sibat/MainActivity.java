package com.mobdeve.project.sibat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        spEditor.apply();
    }
}