package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int turn = 0,record[] = {2,2,2,2,2,2,2,2,2};
    boolean gameState = true;
    int winningPosn[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void insert(View view){
        ImageView cursor = (ImageView)view;
        int position  = Integer.parseInt(cursor.getTag().toString());
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear);
        TextView winnermsg = (TextView)findViewById(R.id.textView);
        if(record[position] == 2 && gameState) {
            cursor.setTranslationY(-1000f);
            record[position] = turn;
            if (turn == 0) {
                cursor.setImageResource(R.drawable.apple2);
                turn = 1;
            } else {
                cursor.setImageResource(R.drawable.brocolli);
                turn = 0;
            }
            cursor.animate().translationYBy(1000f).setDuration(700);
            for(int[] winningPosn: winningPosn){
                if(record[winningPosn[0]] == record[winningPosn[1]] &&
                record[winningPosn[1]] == record[winningPosn[2]] && record[winningPosn[0]] != 2){
                    gameState = false;
                    String msg = "A has Won !!";
                    linearLayout.setVisibility(view.VISIBLE);
                    linearLayout.setBackgroundColor(Color.parseColor("#F80404"));
                    if(record[winningPosn[0]] == 1){
                        msg = "B has Won !!";
                        linearLayout.setBackgroundColor(Color.parseColor("#1BFF05"));
                    }
                    winnermsg.setText(msg);
                }
            }
        }
        else{
            linearLayout.setVisibility(view.VISIBLE);
            linearLayout.setBackgroundColor(Color.parseColor("#E6D813"));
            winnermsg.setText("DRAW");
        }
    }

    public void start(View view){
        GridLayout grid = (GridLayout)findViewById(R.id.board);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear);
        linearLayout.setVisibility(view.INVISIBLE);
        gameState=true;
        int i;
        for(i=0;i < record.length;i++){
            record[i] = 2;
        }

        for(i = 0;i<grid.getChildCount();i++){
//            getting image in each cell of the grid and setting it empty
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
