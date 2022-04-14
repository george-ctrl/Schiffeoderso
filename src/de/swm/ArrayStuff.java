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

    public boolean checkStone(int startX, int startY, int shipLength, ArrayStuff arrayStuff, int rotation){
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


    public boolean checkStoneNew(int startX, int startY, int shipLength, ArrayStuff arrayStuff, int rotation){

        int[][] tempTable = arrayStuff.getPlayerBoardOne();

        if(rotation == 1) {
            if(startX + shipLength > 10){
                return false;
            }
            if (startX > 0 ){
                if(tempTable[startX - 1][startY] == 1){
                    return false;
                }
                else if (startY - 1 > 0 && tempTable[startX - 1][startY-1] == 1){
                    return false;
                }
                else if (startY + 1 < 10 && tempTable[startX - 1][startY+1] == 1){
                    return false;
                }
            }
            if (tempTable[startX+ shipLength][startY] == 1){
                return false;
            }
            if (startY + 1 == 10) {
                for (int i = 0; i < shipLength ; i++) {
                    if( tempTable[startX+i][startY-1] == 1||tempTable[startX+i][startY] == 1){
                        return false;
                    }
                }
            }
            else if (startY - 1 == -1) {
                for (int i = 0; i < shipLength ; i++) {
                    if( tempTable[startX+i][startY+1] == 1||tempTable[startX+i][startY] == 1){
                        return false;
                    }
                }
            }
            else {
                for (int i = 0; i < shipLength ; i++) {
                    if( tempTable[startX+i][startY+1] == 1 ||tempTable[startX+i][startY-1] == 1||tempTable[startX+i][startY] == 1){
                        return false;
                    }
                }
            }
        }
        else if(rotation == 0){
            if(startY + shipLength > 10){
                return false;
            }
            if(startY + shipLength < 10){
                if (startY > 0 ){
                    if(tempTable[startY - 1][startX] == 1){
                        return false;
                    }
                    else if (startX - 1 > 0 && tempTable[startY - 1][startX-1] == 1){
                        return false;
                    }
                    else if (startX + 1 < 10 && tempTable[startY - 1][startX+1] == 1){
                        return false;
                    }
                }

                if(tempTable[startX][startY+ shipLength] == 1){
                    return false;
                }
                if (startX + 1 == 10) {
                    for (int i = 0; i < shipLength ; i++) {
                        if( tempTable[startX-1][startY+i] == 1||tempTable[startX][startY+i] == 1){
                            return false;
                        }
                    }
                }
                else if (startX - 1 == -1) {
                    for (int i = 0; i < shipLength ; i++) {
                        if( tempTable[startX+1][startY+i] == 1||tempTable[startX][startY+i] == 1){
                            return false;
                        }
                    }
                }
                else {
                    for (int i = 0; i < shipLength ; i++) {
                        if( tempTable[startX+1][startY+i] == 1 ||tempTable[startX-1][startY+i] == 1||tempTable[startX][startY+i] == 1){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }



    public boolean checkForHit(int x, int y, Ship[] ships){
        for (int i = 0; i <ships.length ; i++) {
            int[][] tempArray= ships[i].getShipCoordinates();
            if (tempArray[x][y] == 1){
                ships[i].setLength(ships[i].getLength() -1);
                ships[i].shipHitCords(x,y );
                return true;
            }
        }
        return false;
    }



}


