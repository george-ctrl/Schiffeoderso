package de.swm;

import javax.swing.*;
import java.awt.*;

public class Controller {

    private int rotation = 1;
    private int currentShip = 0;
    private int gameState = 0;
    private Board board = new Board();
    private int turnTimer = 0;



    private View view;

    public Controller(View view) {
        this.view = view;
        initAction();
    }

    public void initAction() {
        board.shipBuilder(1);
        board.shipBuilder(2);

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
                    if(gameState == 0 && !board.getCurrentPlayerShip(currentShip).getIsPlaced()){
                        if(board.checkStoneNew(finalI, finalJ, board.getCurrentPlayerShip(currentShip).getLength(), board, rotation)){
                            board.getCurrentPlayerShip(currentShip).setShipCoordinates(finalI, finalJ, board.getCurrentPlayerShip(currentShip).getLength(),rotation);
                            board.getCurrentEnemyShip(currentShip).setShipCoordinates(finalI, finalJ, board.getCurrentEnemyShip(currentShip).getLength(),rotation);
                            board.redoPlayerBoardOne(board.getPlayerShips());
                            //System.out.println(Arrays.deepToString(arrayStuff.getCurrentPlayerShip(currentShip).getShipCoordinates()));
                            //System.out.println(Arrays.deepToString(arrayStuff.getPlayerBoardOne()));
                            redrawPlayerboardOne(jButtons);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Zu nahe an anderen Schiffen oder Rand");
                        }
                    }
                    else if(gameState == 0 && board.getCurrentPlayerShip(currentShip).getIsPlaced()){
                        JOptionPane.showMessageDialog(null, "Dieses Schiff wurde schon gesetzt");
                    }
                });
            }
        }
    }

    private void addActionListenersTop(JButton[][] jButtons) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                jButtons[i + 1][j + 1].addActionListener(e -> {
                    if (turnTimer % 2 == 0) {
                        if (gameState == 1) {
                            board.redoPlayerBoardTwo(board.getEnemyShips());
                            board.shoot(finalI, finalJ);
                            if (!board.checkForHit(finalI, finalJ, board.getEnemyShips())) {
                                turnTimer++;
                            }
                            //System.out.println(Arrays.deepToString(arrayStuff.getCurrentPlayerShip(currentShip).getShipCoordinates()));
                            //System.out.println(Arrays.deepToString(arrayStuff.getPlayerBoardOne()));
                            redrawPlayerboardTwo(jButtons);

                        }
                    }
                });
            }
        }
    }

    private void redrawPlayerboardOne(JButton[][] jButtons){
        int[][] playerBoardOne = board.getPlayerBoardOne();
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
        int[][] playerBoardTwo = board.getPlayerBoardTwo();
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


}


