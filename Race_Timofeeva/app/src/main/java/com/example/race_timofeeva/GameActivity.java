package com.example.race_timofeeva;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private ImageView iv_pccar, iv_usercar, iv_finishline;
    private TextView iv_timer, iv_semafor;
    Timer timerGame = new Timer();
    Timer timerPc = new Timer();
    int state = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        iv_pccar = (ImageView) findViewById(R.id.iv_pccar);
        iv_usercar = (ImageView) findViewById(R.id.iv_usercar);
        iv_finishline = (ImageView) findViewById(R.id.iv_finishline);
        iv_timer = (TextView) findViewById(R.id.iv_timer);
        iv_semafor = (TextView) findViewById(R.id.iv_semafor);
    }

    public void startGameAction(View view) {
        timerGame();
        timerPc();
    }

    public  void driveCarAction(View view) {
        if (state == 2) {
            iv_usercar.setX(iv_usercar.getX() + 30);
        }
        if (state == 1) {
            iv_usercar.setX(iv_usercar.getX() - 30);
        }
        if (iv_usercar.getX() >= iv_finishline.getX()) {
            timerPc.cancel();
            timerGame.cancel();
            Toast.makeText(getApplicationContext(), "YOU WIN", Toast.LENGTH_LONG).show();
        }
    }

    public void timerGame() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        state +=1;
                        if (state > 2)
                            state = 1;
                        switch (state) {
                            case 1:
                                iv_semafor.setText("RED");
                                iv_semafor.setTextColor(Color.RED);
                                break;
                            case 2:
                                iv_semafor.setText("GREEN");
                                iv_semafor.setTextColor(Color.GREEN);
                                break;
                        }
                    }
                });
            }
        };
        timerGame.scheduleAtFixedRate(timerTask, 0, 3000);
    }

    public void timerPc() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (state ==2) {
                            iv_pccar.setX(iv_pccar.getX() + 300);
                        }
                        if (iv_pccar.getX() >= iv_finishline.getX()) {
                            timerPc.cancel();
                            timerGame.cancel();
                            Toast.makeText(getApplicationContext(), "YOU LOSE!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        };
        timerPc.scheduleAtFixedRate(timerTask, 0, 1000);
    }


}