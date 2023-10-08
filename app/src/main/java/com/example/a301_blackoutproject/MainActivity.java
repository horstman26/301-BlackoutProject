package com.example.a301_blackoutproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

//Isabella Horstmanshof
//10/7/2023
//code for class 301, lights out game

public class MainActivity extends AppCompatActivity {
    //needed variables
    public int randNum;
    public Random rand;

    //arrays of button id's and their checkList boolean
    public Button[] buttonList = new Button[25];
    public boolean[] checkList = new boolean[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = findViewById(R.id.bigLayout);

        //makes the background pink
        setTheBackgroundColor(0);

        //button array is filled
        buttonList[0] = findViewById(R.id.button);
        buttonList[1] = findViewById(R.id.button2);
        buttonList[2] = findViewById(R.id.button3);
        buttonList[3] = findViewById(R.id.button4);
        buttonList[4] = findViewById(R.id.button5);
        buttonList[5] = findViewById(R.id.button6);
        buttonList[6] = findViewById(R.id.button7);
        buttonList[7] = findViewById(R.id.button8);
        buttonList[8] = findViewById(R.id.button9);
        buttonList[9] = findViewById(R.id.button10);
        buttonList[10] = findViewById(R.id.button11);
        buttonList[11] = findViewById(R.id.button12);
        buttonList[12] = findViewById(R.id.button13);
        buttonList[13] = findViewById(R.id.button14);
        buttonList[14] = findViewById(R.id.button15);
        buttonList[15] = findViewById(R.id.button16);
        buttonList[16] = findViewById(R.id.button17);
        buttonList[17] = findViewById(R.id.button18);
        buttonList[18] = findViewById(R.id.button19);
        buttonList[19] = findViewById(R.id.button20);
        buttonList[20] = findViewById(R.id.button21);
        buttonList[21] = findViewById(R.id.button22);
        buttonList[22] = findViewById(R.id.button23);
        buttonList[23] = findViewById(R.id.button24);
        buttonList[24] = findViewById(R.id.button25);

        //gives the buttons a random true or false
        setButtons();

        //set up the reset button
        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gives a new true or false to each button and resets the background color
                setButtons();
                setTheBackgroundColor(0);
            }
        });

        //set an onClickListener for each button
        for(int i = 0; i < 25; i++){
            //need this so that i (now j) can be used in the OnClick method
            final int j = i;
            buttonList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //change the color of the button
                    changeButtonColorsOnClick(j);

                    //check if they won the game, if any button is white winGame will become false
                    if (isGameWon() == true){
                        setTheBackgroundColor(1);
                    }

                }
            });

        }

    }

    private void setTheBackgroundColor(int i) {
        View theView = this.getWindow().getDecorView();
        if(i == 0){
            theView.setBackgroundColor(Color.parseColor("#ffa3d2"));
        }
        else {
            theView.setBackgroundColor(Color.parseColor("#ca80e9"));
        }
    }

    private boolean isGameWon(){
        //if any button is white, return false. otherwise return true
        for (int i = 0; i < 25; i++){
            if (checkList[i] == true){
                return false;
            }
        }
        return true;
    }

    private void setButtons(){
        //cycle through checkList and assign true or false based on a random number
        for(int i = 0; i < 25; i++){
            rand = new Random();
            randNum = rand.nextInt(11);
            checkList[i] = randNum % 2 == 0;

        }
        //cycle through buttons and make check true(white) or false(black)
        for (int i = 0; i < 25; i++){
            if(checkList[i]) {
                buttonList[i].setBackgroundColor(Color.WHITE);
            }
            else{
                buttonList[i].setBackgroundColor(Color.BLACK);
            }
        }

    }


    private void changeButtonColorsOnClick(int j) {
        //change the color of the button clicked on
        if (checkList[j] == true) {
            buttonList[j].setBackgroundColor(Color.BLACK);
            checkList[j] = false;
        } else {
            buttonList[j].setBackgroundColor(Color.WHITE);
            checkList[j] = true;
        }
        //if button is left side but not top or bottom
        if ((j == 5) || (j ==10) || (j == 15)){
            //one to the right
            addOne(j);
            //one on top
            loseFive(j);
            //one on bottom
            addFive(j);
        }
        //if button is top but not left or right
        else if ((j == 1) || (j == 2) || (j == 3)){
            //one on bottom
            addFive(j);
            //one on left
            loseOne(j);
            //one on right
            addOne(j);
        }
        //if button is bottom but not left or right
        else if ((j == 21) || (j == 22) || (j == 23)){
            //one on top
            loseFive(j);
            //one left
            loseOne(j);
            //one right
            addOne(j);
        }
        //if button is right but not top or bottom
        else if((j == 9) || (j == 14) || (j == 19)){
            //one on top
            loseFive(j);
            //one on bottom
            addFive(j);
            //one left
            loseOne(j);
        }
        //if button is top left
        else if (j == 0){
            //one right
            addOne(j);
            //one bottom
            addFive(j);
        }
        //if button is top right
        else if (j == 4){
            //one left
            loseOne(j);
            //one bottom
            addFive(j);

        }
        //if button is bottom left
        else if (j == 20){
            //one right
            addOne(j);
            //one top
            loseFive(j);
        }
        //if button is bottom right
        else if (j == 24){
            //one left
            loseOne(j);
            //one top
            loseFive(j);
        }
        //else if button is anything else
        else{
            //one top
            loseFive(j);
            //one bottom
            addFive(j);
            //one left
            loseOne(j);
            //one right
            addOne(j);
        }

    }

    //these four methods are used to get the values of the top, bottom, right, or left button of the button at j
    private void addFive(int j){
        if(checkList[j+5] == true){
            buttonList[j+5].setBackgroundColor(Color.BLACK);
            checkList[j+5] = false;
        }
        else{
            buttonList[j+5].setBackgroundColor(Color.WHITE);
            checkList[j+5] = true;
        }
    }

    private void loseFive(int j){
        if(checkList[j-5] == true){
            buttonList[j-5].setBackgroundColor(Color.BLACK);
            checkList[j-5] = false;
        }
        else{
            buttonList[j-5].setBackgroundColor(Color.WHITE);
            checkList[j-5] = true;
        }
    }

    private void addOne(int j){
        if(checkList[j+1] == true){
            buttonList[j+1].setBackgroundColor(Color.BLACK);
            checkList[j+1] = false;
        }
        else{
            buttonList[j+1].setBackgroundColor(Color.WHITE);
            checkList[j+1] = true;
        }
    }

    private void loseOne(int j){
        if(checkList[j-1] == true){
            buttonList[j-1].setBackgroundColor(Color.BLACK);
            checkList[j-1] = false;
        }
        else{
            buttonList[j-1].setBackgroundColor(Color.WHITE);
            checkList[j-1] = true;
        }
    }

}
