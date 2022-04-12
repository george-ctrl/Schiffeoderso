package de.swm;

public class Ship {
    private int length;
    private boolean isAlive;
    private int[][] shipCoordinates;
    private boolean isPlaced;
    private ArrayStuff arrayStuff = new ArrayStuff();




    public Ship(int length) {
        this.length = length;
        this.isAlive = true;
        this.shipCoordinates = new int[10][10];
        this.isPlaced = false;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length){
        this.length = length;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public void setShipCoordinates(int startX, int startY, int shipLength, int orientation) {




        if(orientation == 1 && startX + shipLength < 11){
            for (int i = 0; i < shipLength ; i++) {
                this.shipCoordinates[startX+i][startY] = 1;
                this.isPlaced = true;
            }
        }
        else if(orientation == 0 && startY + shipLength < 11){
            for (int i = 0; i < shipLength ; i++) {
                this.shipCoordinates[startX][startY+i] = 1;
                this.isPlaced = true;
            }
        }
    }


    public int[][] getShipCoordinates(){
        return this.shipCoordinates;
    }


    public void checkForHit(int x, int y){
        if(this.shipCoordinates[x][y] == 1){
            this.shipCoordinates[x][y] = 2;
            this.length -= 1;
        }
    }

    public boolean checkIsAlive(){
        if (this.length == 0){
            this.isAlive = false;
            return false;
        }
        else {
            return true;
        }
    }
    public boolean getIsPlaced(){
        return this.isPlaced;
    }

    public boolean checkPlace(int startX, int startY, int shipLength, int orientation){
        return true;
    }


}
