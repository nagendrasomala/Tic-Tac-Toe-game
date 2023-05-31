package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button newgame;
    MediaPlayer music;
private boolean player1 = true;
Button buttons[][] = new Button[3][3];
int rounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newgame = findViewById(R.id.button);
         music = MediaPlayer.create(MainActivity.this, R.raw.gamemusic);
         music.setLooping(true);
         music.start();
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                String a = "but" + i + j;
                int resid = getResources().getIdentifier(a, "id", getPackageName());
                buttons[i][j] = findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.start();
                for(int i=0;i<3;i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");

                    }
                    }
                rounds=0;
                player1=true;

            }
        });

    }


    @Override
    public void onClick(View v) {
            if(!((Button)v).getText().toString().equals("")){
                return;
            }
            if(player1){
                ((Button)v).setText("X");
            }
            else{
                ((Button)v).setText("O");

            }
            rounds++;
            if(win()){

                if(player1){
                    music.pause();
                    player1win();
                }
                else{
                    music.pause();
                    player2win();
                }
            }
            else if(rounds==9){
                draw();
            }
            else{
                player1 = !player1;
            }
        }


    private boolean win(){
        String[][] field = new String[3][3];
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j]=buttons[i][j].getText().toString();
            }
            }
        for (int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!field[i][0].equals("")){
                return  true;
            }

        }
        for (int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&!field[0][i].equals("")){
                return  true;
            }

        }
        if(field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&!field[0][0].equals("")){
            return  true;
        }
        if(field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])&&!field[0][2].equals("")){
            return  true;
        }
        return false;
    }

    private void player1win(){
        MediaPlayer music1 = MediaPlayer.create(MainActivity.this, R.raw.winner);
        music1.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Player 1 Winner");
        builder.setIcon(R.drawable.ic_baseline_celebration_24);
        builder.setMessage("");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                music.start();
                for(int i=0;i<3;i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");

                    }
                }
                rounds=0;
                player1=true;
            }
        });
        builder.create();
        builder.show();

    }

    private void player2win(){
        MediaPlayer music2 = MediaPlayer.create(MainActivity.this, R.raw.winner);
        music2.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Player 2 Winner");
        builder.setIcon(R.drawable.ic_baseline_celebration_24);
        builder.setMessage("");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                music.start();
                for(int i=0;i<3;i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");

                    }
                }
                rounds=0;
                player1=true;

            }
        });
        builder.create();
        builder.show();


    }

    private void draw(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Match Draw");
        builder.setIcon(R.drawable.ic_baseline_celebration_24);
        builder.setMessage("");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                music.start();
                for(int i=0;i<3;i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");

                    }
                }
                rounds=0;
                player1=true;
            }
        });
        builder.create();
        builder.show();


    }
}