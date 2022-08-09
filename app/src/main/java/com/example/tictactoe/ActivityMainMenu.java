package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMainMenu extends AppCompatActivity {
    Button singlePlayer;
    Button twoPlayer;
    int selMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        singlePlayer = findViewById(R.id.btnSinglePlayer);
        twoPlayer = findViewById(R.id.btnTwoPlayer);

        Intent iPlayGame = new Intent(ActivityMainMenu.this,
                ActivityPlayTicTacToe.class);

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selMode = 0;
                iPlayGame.putExtra("selMode", selMode);
                startActivity(iPlayGame);
            }
        });
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selMode = 1;
                iPlayGame.putExtra("selMode", selMode);
                startActivity(iPlayGame);
            }
        });
    }
}