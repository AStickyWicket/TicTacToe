package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Arrays;
import java.util.Random;

public class ActivityPlayTicTacToe extends AppCompatActivity {
    boolean disableBoard = false;
    int selMode;
    boolean gameWin;
    Button playAgain;
    Random rand = new Random();
    //0 = X 1 = O
    int currentPlayer = 0;
    int[] currentBoard = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winConditions = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    int[] imgViews = new int[] { R.id.imgvTopLeft, R.id.imgvTopMiddle, R.id.imgvTopRight,
    R.id.imgvMiddleLeft, R.id.imgvMiddle, R.id.imgvMiddleRight,
    R.id.imgvBottomLeft, R.id.imgvBottomMiddle, R.id.imgvBottomRight};

    int turnCounter = 0;

    public void resetBoard(View view) {
        disableBoard = false;
        currentPlayer = 0;
        turnCounter = 0;
        Arrays.fill(currentBoard, 2);

        findViewById(R.id.btnPlayAgain).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.imgvTopLeft)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvTopMiddle)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvTopRight)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvMiddleLeft)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvMiddle)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvMiddleRight)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvBottomLeft)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvBottomMiddle)).setImageResource(0);
        ((ImageView) findViewById(R.id.imgvBottomRight)).setImageResource(0);
    }

    public void onPlayerClick(View view) {
        ImageView img = (ImageView) view;
        int selectedImg = Integer.parseInt(img.getTag().toString());
        playAgain = findViewById(R.id.btnPlayAgain);

        if (!disableBoard) {

            if (currentBoard[selectedImg] == 2) {
                currentBoard[selectedImg] = currentPlayer;
                turnCounter++;

                if (currentPlayer == 0) {
                    img.setImageResource(R.drawable.img_x);
                    currentPlayer = 1;
                } else {
                    img.setImageResource(R.drawable.img_o);
                    currentPlayer = 0;
                }

                gameWin = false;
                if(!checkWin() && selMode == 0 && currentPlayer == 1){
                    int robotChoice = rand.nextInt(9);
                    while (currentBoard[robotChoice] != 2) {
                        robotChoice = rand.nextInt(9);
                    }

                    (new Handler()).postDelayed(null,5000);
                    onPlayerClick(findViewById(imgViews[robotChoice]));

                }


                if (turnCounter == 9 && !gameWin) {
                    Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show();
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private boolean checkWin(){
        for (int[] winCondition : winConditions) {
            if (currentBoard[winCondition[0]] == currentBoard[winCondition[1]] &&
                    currentBoard[winCondition[1]] == currentBoard[winCondition[2]]
                    && currentBoard[winCondition[0]] != 2) {
                gameWin = true;
                playAgain.setVisibility(View.VISIBLE);
                disableBoard = true;

                if (currentBoard[winCondition[0]] == 0) {
                    Toast.makeText(this, "X wins!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "O wins!", Toast.LENGTH_LONG).show();
                }
            }
        }
        return gameWin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        selMode = intent.getIntExtra("selMode", 0);
    }
}