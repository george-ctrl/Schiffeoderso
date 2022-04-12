package de.swm;

import java.util.Arrays;

public class ArrayStuff {

    private int[][] playerBoardOne = new int[10][10];
    private int[][] playerBoardTwo = new int[10][10];
    private int[][] enemyBoardOne = new int[10][10];
    private int[][] enemyBoardTwo = new int[10][10];
    private Ship[] playerShips = new Ship[7];
    private Ship[] enemyShips = new Ship[7];


    public void redoPlayerBoardOne(Ship[] ships){
        for (int i = 0; i < ships.length ; i++) {
            int[][] tempArray = ships[i].getShipCoordinates();
            for (int j = 0; j <10 ; j++) {
                for (int k = 0; k <10; k++) {
                    switch (tempArray[j][k]){
                        case 1:
                            this.playerBoardOne[j][k] = 1;
                            break;
                        case 2:
                            this.playerBoardOne[j][k] = 2;
                            break;
                        case 3:
                            this.playerBoardOne[j][k] = 3;
                            break;
                    }
                }
            }
        }
    }
    public void redoPlayerBoardTwo(Ship[] ships){
        for (int i = 0; i < ships.length ; i++) {
            int[][] tempArray = ships[i].getShipCoordinates();
            for (int j = 0; j <10 ; j++) {
                for (int k = 0; k <10; k++) {
                    switch (tempArray[j][k]){
                        case 1:
                            this.playerBoardTwo[j][k] = 1;
                            break;
                        case 2:
                            this.playerBoardTwo[j][k] = 2;
                            break;
                        case 3:
                            this.playerBoardTwo[j][k] = 3;
                            break;
                    }
                }
            }
        }
    }


    public void shoot(int x, int y){
        if(playerBoardTwo[x][y] == 0){
            playerBoardTwo[x][y] = 3;
        }
        else if(playerBoardTwo[x][y] == 1){
            playerBoardTwo[x][y] = 2;
        }
    }


    public void redoEnemyBoards(){

    }

    public void shipBuilder(int player){
        if (player == 1 ){
            playerShips[0] = new Ship(5);
            playerShips[1] = new Ship(4);
            playerShips[2] = new Ship(4);
            playerShips[3] = new Ship(3);
            playerShips[4] = new Ship(3);
            playerShips[5] = new Ship(3);
            playerShips[6] = new Ship(2);
        }
        if (player == 2 ){
            enemyShips[0] = new Ship(5);
            enemyShips[1] = new Ship(4);
            enemyShips[2] = new Ship(4);
            enemyShips[3] = new Ship(3);
            enemyShips[4] = new Ship(3);
            enemyShips[5] = new Ship(3);
            enemyShips[6] = new Ship(2);
        }
    }

    public Ship[] getPlayerShips(){
        return playerShips;
    }

    public Ship[] getEnemyShips(){
        return enemyShips;
    }
    public Ship getCurrentPlayerShip(int i){
        return playerShips[i];
    }
    public Ship getCurrentEnemyShip(int i){
        return enemyShips[i];
    }

    public int[][] getPlayerBoardOne() {
        return playerBoardOne;
    }

    @Override
    public String toString() {
        return "ArrayStuff{" +
                "playerBoardOne=" + Arrays.toString(playerBoardOne) +
                '}';
    }

    public int[][] getPlayerBoardTwo() {
        return playerBoardTwo;
    }
}
