package de.swm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Controller {

    private int rotation = 1;
    private int currentShip = 0;
    private int gameState = 0;
    private ArrayStuff arrayStuff = new ArrayStuff();


    private View view;

    public Controller(View view) {
        this.view = view;
        initAction();
    }

    public void initAction() {
        arrayStuff.shipBuilder(1);
        arrayStuff.shipBuilder(2);

        view.getRotateBtn().addActionListener(e -> {
            if (rotation == 1) {
                rotation = 0;
                view.setLblInfoRota("Horizontal");
            } else {
                rotation = 1;
                view.setLblInfoRota("Vertikal");
            }
        });

        view.getChangeShip().addActionListener(e -> {
            if (currentShip < 6){
                currentShip += 1;
            }
            else{
                currentShip = 0;
            }
            view.getLblInfoShip().setText("Schiff " + (currentShip + 1));

        });

        view.getDeployBtn().addActionListener(e -> {
            gameState = 1;
            view.getDeployBtn().setEnabled(false);
            view.getRotateBtn().setEnabled(false);
            view.getChangeShip().setEnabled(false);
        });

        addActionListenersBot(view.getButtonsBot());
        addActionListenersTop(view.getButtonsTop());


    }


    private void addActionListenersBot(JButton[][] jButtons) {

        for(int i = 0; i<10;i++) {
            for(int j = 0; j<10; j++) {
                int finalI = i;
                int finalJ = j;
                jButtons[i+1][j+1].addActionListener(e -> {
                    if(gameState == 0 && !arrayStuff.getCurrentPlayerShip(currentShip).getIsPlaced()){
                        if(checkStone(finalI, finalJ, arrayStuff.getCurrentPlayerShip(currentShip).getLength())){
                            arrayStuff.getCurrentPlayerShip(currentShip).setShipCoordinates(finalI, finalJ,arrayStuff.getCurrentPlayerShip(currentShip).getLength(),rotation);
                            arrayStuff.getCurrentEnemyShip(currentShip).setShipCoordinates(finalI, finalJ,arrayStuff.getCurrentEnemyShip(currentShip).getLength(),rotation);
                            arrayStuff.redoPlayerBoardOne(arrayStuff.getPlayerShips());
                            //System.out.println(Arrays.deepToString(arrayStuff.getCurrentPlayerShip(currentShip).getShipCoordinates()));
                            //System.out.println(Arrays.deepToString(arrayStuff.getPlayerBoardOne()));
                            redrawPlayerboardOne(jButtons);
                        }
                        else {
                            System.out.println("Zu nahe");
                        }
                    }
                    else if(gameState == 0 && arrayStuff.getCurrentPlayerShip(currentShip).getIsPlaced()){
                        System.out.println("schon gesetzt");
                    }
                });
            }
        }
    }
    private void addActionListenersTop(JButton[][] jButtons) {

        for(int i = 0; i<10;i++) {
            for(int j = 0; j<10; j++) {
                int finalI = i;
                int finalJ = j;
                jButtons[i+1][j+1].addActionListener(e -> {
                    if(gameState == 1 ){
                        arrayStuff.redoPlayerBoardTwo(arrayStuff.getEnemyShips());
                        arrayStuff.shoot(finalI, finalJ);
                        //System.out.println(Arrays.deepToString(arrayStuff.getCurrentPlayerShip(currentShip).getShipCoordinates()));
                        //System.out.println(Arrays.deepToString(arrayStuff.getPlayerBoardOne()));
                        redrawPlayerboardTwo(jButtons);

                    }
                    else if(gameState == 0 && arrayStuff.getCurrentPlayerShip(currentShip).getIsPlaced()){
                        System.out.println("schon gesetzt");
                    }
                });
            }
        }
    }

    private void redrawPlayerboardOne(JButton[][] jButtons){
        int[][] playerBoardOne = arrayStuff.getPlayerBoardOne();
        for (int j = 0; j <10 ; j++) {
            for (int k = 0; k <10; k++) {
                switch (playerBoardOne[j][k]){
                    case 1:
                        jButtons[j+1][k+1].setBackground(Color.gray);
                        break;
                }
            }
        }
    }

    private void redrawPlayerboardTwo(JButton[][] jButtons){
        int[][] playerBoardTwo = arrayStuff.getPlayerBoardTwo();
        for (int j = 0; j <10 ; j++) {
            for (int k = 0; k <10; k++) {
                switch (playerBoardTwo[j][k]){
                    case 2:
                        jButtons[j+1][k+1].setBackground(Color.green);
                        break;
                    case 3:
                        jButtons[j+1][k+1].setBackground(Color.blue);
                        break;
                }
            }
        }
    }



    private boolean checkStone(int startX, int startY, int shipLength){
        int[][] tempTable = arrayStuff.getPlayerBoardOne();

        if(tempTable[startX][startY] == 1){
            return false;
        }
        if(rotation == 1){
            if(startX + shipLength > 10){
                return false;
            }
            if(startX + shipLength < 9 && tempTable[startX+shipLength][startY] == 1){
                return false;
            }
            for (int i = 0; i < shipLength ; i++) {
                if(startY < 10 && (tempTable[startX+i][startY+1] == 1||tempTable[startX+i][startY] == 1)){
                    return false;
                }
                else if(startY > 0 && (tempTable[startX+i][startY-1] == 1||tempTable[startX+i][startY] == 1)){
                    return false;
                }
            }
        }
        else if(rotation == 0){
            if (startY + shipLength > 10){
                return false;
            }
            if (startY + shipLength < 9 && tempTable[startX][startY+shipLength] == 1){
                return false;
            }
            for (int i = 0; i < shipLength ; i++) {
                if(startX < 10 && (tempTable[startX+1][startY+i] == 1 || tempTable[startX][startY+i] == 1)){
                    return false;
                }
                else  if(startX > 0 && (tempTable[startX-1][startY+i] == 1 || tempTable[startX][startY+i] == 1)){
                    return false;
                }
            }
        }
        return true;
    }


       /*if(rotation == 1 && finalI + arrayStuff.getCurrentPlayerShip(currentShip).getLength() < 11){
           for (int i = 0; i <arrayStuff.getCurrentPlayerShip(currentShip).getLength()  ; i++) {
               jButtons[finalI+i+1][finalJ+1].setBackground(Color.gray);
           }
       }
       if(rotation == 0 && finalJ + arrayStuff.getCurrentPlayerShip(currentShip).getLength() < 11){
           for (int i = 0; i < arrayStuff.getCurrentPlayerShip(currentShip).getLength() ; i++) {
               jButtons[finalI+1][finalJ+i+1].setBackground(Color.gray);
           }
       }*/


}


