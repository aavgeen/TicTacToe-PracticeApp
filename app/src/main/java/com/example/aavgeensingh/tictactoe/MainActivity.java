package com.example.aavgeensingh.tictactoe;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final static int NO_PLAYER = 0;
    public final static int PLAYER1 = 1;
    public final static int PLAYER2 = 2;
    public final static int PLAYER_1_WINS = 1;
    public final static int PLAYER_2_WINS = 2;
    public final static int DRAW = 3;
    public final static int INCOMPLETE = 4;
    int n = 4;
    boolean player1Turn=true;
    boolean gameOver=false ;
    Cells[][] cell;
    LinearLayout bigger;
    LinearLayout rows[];
    RelativeLayout rel;
    TextView txt1;
    TextView txt2;
    static int win1=0;
    static int win2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bigger= (LinearLayout) findViewById(R.id.bigger);
        rel=(RelativeLayout)findViewById(R.id.rel);
        txt1=(TextView)findViewById(R.id.textView1);
        txt2=(TextView)findViewById(R.id.textView2);
        txt1.setText("Player 1 Won 0 Times");
        txt2.setText("Player 2 Won 0 Times");
        //bigger.setOrientation(LinearLayout.HORIZONTAL);
        setUpBoard();
        resetBoard();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.newGame){
            resetBoard();
        }
     /*   else if(id==R.id.custom){   //TODO... ask mam
            show();
            setUpBoard();
            resetBoard();
        }
        */
        else if(id==R.id.three){
            n=3;
            setUpBoard();
            resetBoard();
        }
        else if(id==R.id.four){
            n=4;
            setUpBoard();
            resetBoard();
        }
        else if(id==R.id.five){
            n=5;
            setUpBoard();
            resetBoard();
        }
        else if(id==R.id.six){
            n=6;
            setUpBoard();
            resetBoard();
        }

        return true;
    }

    private void setUpBoard() {
        player1Turn = true;
        gameOver=false;
        cell=new Cells[n][n];
        rows=new LinearLayout[n];
        bigger.removeAllViews();
        for (int i = 0; i < n; i++) {
            rows[i]=new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            params.setMargins(5, 5, 5, 5);
            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            bigger.addView(rows[i]);
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                cell[j][i]=new Cells(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(5, 5, 5, 5);
               /* if(j==0 && i==0)
                    params.setMargins(0,0,5,5);
                else if(j==n-1 && i==0)
                    params.setMargins(0,5,5,0);
                else if(j==0 && i==n-1)
                    params.setMargins(5,0,0,5);
                else if(j==n-1 && i==n-1)
                    params.setMargins(5,5,0,0);
                else {
                    if(j==0)
                        params.setMargins(5,0,5,5);
                    if(j==n-1)
                        params.setMargins(5,5,5,0);
                    if(i==0)
                        params.setMargins(0,5,5,5);
                    if(i==n-1)
                        params.setMargins(5,5,0,5);
                }
                */
                cell[j][i].setLayoutParams(params);
                cell[j][i].setText("");
                cell[j][i].setPlayer(NO_PLAYER);
                cell[j][i].setOnClickListener(this);
                //cell[j][i].setShadowLayer(1,1,1,Color.GRAY);
                cell[j][i].setPadding(0,0,0,0);
                cell[j][i].setBackgroundColor(Color.rgb(24,116,205));
                cell[j][i].setTextColor(Color.WHITE);

                rows[i].addView(cell[j][i]);
            }
        }

    }

    private void resetBoard() {
        player1Turn = true;
        gameOver = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cell[i][j].setText("");
                cell[i][j].setPlayer(NO_PLAYER);
                int height = cell[i][j].getWidth();
                cell[i][j].setHeight(height);
                cell[i][j].setPadding(0,0,0,0);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Cells curr=(Cells)v;
        if(gameOver)
            return;
        if(curr.getPlayer()!=NO_PLAYER){
            Toast.makeText(this,"Already filled", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(player1Turn){
            curr.setPlayer(PLAYER1);
            curr.setText("O");
        }
        else{
            curr.setPlayer(PLAYER2);
            curr.setText("X");
        }

        int status=getStatus();
        if(status==PLAYER_1_WINS) {
            Toast.makeText(this,"PLAYER 1 Wins!!", Toast.LENGTH_SHORT).show();
            win1++;
            txt1.setText("Player1 (O) Won "+win1+" times");
            gameOver=true;
            return;//setUpBoard(); // ASK WITH A PROMPLT TODO
        }
        else if(status==PLAYER_2_WINS){
            Toast.makeText(this,"PLAYER 2 Wins!!",Toast.LENGTH_SHORT).show();
            win2++;
            txt2.setText("Player2 (X) Won "+win2+" times");
            gameOver=true;
            return;//setUpBoard(); // ASK WITH A PROMPLT TODO
        }
        else if(status==DRAW){
            Toast.makeText(this,"GAME DRAW",Toast.LENGTH_SHORT).show();
            gameOver=true;
            return;//setUpBoard(); // ASK WITH A PROMPLT to have a new game.TODO
        }
        player1Turn=!player1Turn;
    }

    public int getStatus() {
        //Check for ROWS
        boolean complete;
        for (int i = 0; i < n; i++) {
            complete=true;
            for (int j = 0; j < n; j++) {
                if(cell[i][j].getPlayer()==NO_PLAYER || cell[i][0].getPlayer()!=cell[i][j].getPlayer()){
                    complete=false;
                    break;
                }
            }
            if(complete){
                if(cell[i][0].getPlayer()==PLAYER1)
                    return PLAYER_1_WINS;
                else
                    return PLAYER_2_WINS;
            }
        }
        //Check for COLUMNS
        for (int i = 0; i < n; i++) {
            complete=true;
            for (int j = 0; j < n; j++) {
                if(cell[j][i].getPlayer()==NO_PLAYER || cell[0][i].getPlayer()!=cell[j][i].getPlayer()) {
                    complete = false;
                    break;
                }
            }
            if(complete){
                if(cell[0][i].getPlayer()==PLAYER1)
                    return PLAYER_1_WINS;
                else
                    return PLAYER_2_WINS;
            }
        }
        //Check for DIAGONALS
        complete=true;
        for (int j = 0; j < n; j++) {
            if(cell[j][j].getPlayer()==NO_PLAYER || cell[0][0].getPlayer()!=cell[j][j].getPlayer()){
                complete=false;
                break;}
        }
        if(complete){
            if(cell[0][0].getPlayer()==PLAYER1)
                return PLAYER_1_WINS;
            else
                return PLAYER_2_WINS;
        }
        // Check for DIAGONAL 2
        complete = true;
        for(int i = n - 1; i >= 0; i--){
            int col = n - 1 - i;
            if (cell[i][col].getPlayer() == NO_PLAYER || cell[n - 1][0].getPlayer() != cell[i][col].getPlayer()) {
                complete = false;
                break;
            }
        }
        if (complete) {
            if (cell[n - 1][0].getPlayer() == PLAYER1) {
                return PLAYER_1_WINS;
            } else {
                return PLAYER_2_WINS;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(cell[i][j].getPlayer()==NO_PLAYER){
                    return INCOMPLETE;
                }
            }
        }
        return DRAW;
    }

}
