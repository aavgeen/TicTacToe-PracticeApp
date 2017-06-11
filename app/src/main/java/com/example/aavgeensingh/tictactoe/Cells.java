package com.example.aavgeensingh.tictactoe;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Aavgeen Singh on 1/30/2017.
 */

public class Cells extends Button{
    private int player;

    public Cells(Context context) {
        super(context);
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
