package de.swm;
import javax.swing.*;
import java.awt.*;

public class View {

    private JFrame frame;
    private JButton[][] buttonsTop = new JButton[11][11];
    private JButton[][] buttonsBot = new JButton[11][11];
    private String[] alphabet = {"A","B","C","D","E","F","G","H","I","J"};
    private JButton changeShip;
    private JButton rotateBtn;
    private JButton deployBtn;
    private JLabel lblInfoRota;
    private JLabel lblInfoShip;
    private JLabel lblInfoShip1;
    private JLabel lblInfoShip2;
    private JLabel lblInfoShip3;
    private JLabel lblInfoShip4;



    public View() {
        frame = new JFrame("demschiffeverzenken");
        frame.setSize(800, 1200);
        frame.setLayout(new BorderLayout() );

        JSplitPane totalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane boardsSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JPanel stuffRight = new JPanel(new GridLayout(20,1));
        JPanel boardTop = new JPanel(new GridLayout(11,11));
        JPanel boardBot = new JPanel(new GridLayout(11,11));

        boardbuilder(buttonsTop, boardTop);
        boardbuilder(buttonsBot, boardBot);


        changeShip = new JButton("Change Ship");
        rotateBtn = new JButton("Rotate");
        deployBtn = new JButton("Deploy");
        lblInfoRota = new JLabel("Vertikal");
        lblInfoShip = new JLabel("Schiff 1");
        lblInfoShip1 = new JLabel("Schiff 1 ist 5 Lang");
        lblInfoShip2 = new JLabel("Schiffe 2 und 3 sind 4 Lang");
        lblInfoShip3 = new JLabel("Schiffe 4,5,6 sind 3 Lang");
        lblInfoShip4 = new JLabel("Schiff 7 ist 2 Lang");
        stuffRight.add(lblInfoRota);
        stuffRight.add(lblInfoShip);
        stuffRight.add(lblInfoShip1);
        stuffRight.add(lblInfoShip2);
        stuffRight.add(lblInfoShip3);
        stuffRight.add(lblInfoShip4);
        stuffRight.add(changeShip);
        stuffRight.add(rotateBtn);
        stuffRight.add(deployBtn);


        boardBot.setSize(new Dimension(600,500));
        boardTop.setSize(new Dimension(600,500));

        boardsSplit.setTopComponent(boardTop);
        boardsSplit.setBottomComponent(boardBot);
        boardsSplit.setResizeWeight(0.5d);
        totalSplit.setLeftComponent(boardsSplit);
        totalSplit.setRightComponent(stuffRight);
        totalSplit.setResizeWeight(0.7d);
        frame.add(totalSplit);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private void boardbuilder(JButton[][] buttonsTop, JPanel boardTop){
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <11; j++) {
                buttonsTop[i][j] = new JButton();
                boardTop.add(buttonsTop[i][j]);
                buttonsTop[i][j].setBackground(Color.white);
            }
        }
        for (int i = 0; i < 10; i++) {
            buttonsTop[0][i+1].setText(Integer.toString(i));
            buttonsTop[0][i+1].setEnabled(false);

        }
        for (int i = 0; i < 10; i++) {
            buttonsTop[i+1][0].setText(alphabet[i]);
            buttonsTop[i+1][0].setEnabled(false);
        }
        buttonsTop[0][0].setEnabled(false);
    }

    public JButton[][] getButtonsTop(){
        return buttonsTop;
    }

    public void setButtonsBot(JButton[][] jButtons){
        this.buttonsBot = jButtons;
    }

    public JButton[][] getButtonsBot() {
        return buttonsBot;
    }

    public void setButtonsTop(JButton[][] buttonsTop) {
        this.buttonsTop = buttonsTop;
    }

    public void setButtonBot(JButton[][] buttonsBot) {
        this.buttonsBot = buttonsBot;
    }

    public void  setButtonTop(JButton j, int i, int y){
        this.buttonsTop[i][y] = j;
    }

    public void  setButtonBot(JButton j, int i, int y){
        this.buttonsBot[i][y] = j;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String[] alphabet) {
        this.alphabet = alphabet;
    }

    public JButton getLargeShipBtn() {
        return changeShip;
    }

    public void setLargeShipBtn(JButton largeShipBtn) {
        this.changeShip = largeShipBtn;
    }

    public JButton getRotateBtn() {
        return rotateBtn;
    }

    public void setRotateBtn(JButton rotateBtn) {
        this.rotateBtn = rotateBtn;
    }

    public JButton getChangeShip() {
        return changeShip;
    }

    public void setChangeShip(JButton changeShip) {
        this.changeShip = changeShip;
    }

    public JLabel getLblInfoRota() {
        return lblInfoRota;
    }

    public void setLblInfoRota(String s) {
        this.lblInfoRota.setText(s);
    }

    public JLabel getLblInfoShip() {
        return lblInfoShip;
    }

    public void setLblInfoShip(String s) {
        this.lblInfoShip.setText(s);
    }

    public JButton getDeployBtn() {
        return deployBtn;
    }

    public void setDeployBtn(JButton deployBtn) {
        this.deployBtn = deployBtn;
    }



    }


