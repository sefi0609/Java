package com.example.fourinarow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class FourInARowController {

    private double hInterval,vInterval;//intervals to draw the game board
    private GraphicsContext g;//object for drawing on the canvas
    private FourInARowLogic game;//object of the logic class for the game
    private boolean gameOn;//indicates if the game is still on

    //the 7 buttons of the game
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button ClearButton;
    @FXML
    private Canvas canvas;
    //create an initial board for the game
    @FXML
    public void initialize(){
        g = canvas.getGraphicsContext2D();
        hInterval = canvas.getWidth() / 7;
        vInterval = canvas.getHeight() / 6;

        for(int i = 1; i < 8; i++)
            g.strokeLine(i*hInterval, 0, i*hInterval, canvas.getHeight());

        for(int j = 1; j < 7; j++)
            g.strokeLine(0, j*vInterval, canvas.getWidth(), j*vInterval);

        game = new FourInARowLogic();
        gameOn = true;
    }
    //showing a massage on the canvas for the winning player
    @FXML
    void ShowTheWinner(){
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.strokeText("The winner is the "+game.turn+" player",100,100);
        gameOn = false;
    }
    //selecting in which box to draw a circle
    @FXML
    void ClickForCircle(ActionEvent event) {
        if(gameOn) {
            if (event.getSource() == button1) {
                int freeBox = game.checkFreeBox(0);
                if (freeBox != -1) {
                    double[] point = game.calculateBox(freeBox + 1, 1, hInterval, vInterval);// point[0] = x,point[1] = y
                    drawCircle(point[0], point[1], game.turn);
                    if (point[2] == 1)
                        ShowTheWinner();
                }
            } else if (event.getSource() == button2) {
                int freeBox = game.checkFreeBox(1);
                if (freeBox != -1) {
                    double[] point = game.calculateBox(freeBox + 1, 2, hInterval, vInterval);// point[0] = x,point[1] = y
                    drawCircle(point[0], point[1], game.turn);
                    if (point[2] == 1)
                        ShowTheWinner();
                }
            } else if (event.getSource() == button3) {
                int freeBox = game.checkFreeBox(2);
                if (freeBox != -1) {
                    double[] point = game.calculateBox(freeBox + 1, 3, hInterval, vInterval);// point[0] = x,point[1] = y
                    drawCircle(point[0], point[1], game.turn);
                    if (point[2] == 1)
                        ShowTheWinner();
                }
            } else if (event.getSource() == button4) {
                int freeBox = game.checkFreeBox(3);
                if (freeBox != -1) {
                    double[] point = game.calculateBox(freeBox + 1, 4, hInterval, vInterval);// point[0] = x,point[1] = y
                    drawCircle(point[0], point[1], game.turn);
                    if (point[2] == 1)
                        ShowTheWinner();
                }
            } else if (event.getSource() == button5) {
                int freeBox = game.checkFreeBox(4);
                if (freeBox != -1) {
                    double[] point = game.calculateBox(freeBox + 1, 5, hInterval, vInterval);// point[0] = x,point[1] = y
                    drawCircle(point[0], point[1], game.turn);
                    if (point[2] == 1)
                        ShowTheWinner();
                }
            } else if (event.getSource() == button6) {
                int freeBox = game.checkFreeBox(5);
                if (freeBox != -1) {
                    double[] point = game.calculateBox(freeBox + 1, 6, hInterval, vInterval);// point[0] = x,point[1] = y
                    drawCircle(point[0], point[1], game.turn);
                    if (point[2] == 1)
                        ShowTheWinner();
                }
            } else if (event.getSource() == button7) {
                int freeBox = game.checkFreeBox(6);
                if (freeBox != -1) {
                    double[] point = game.calculateBox(freeBox + 1, 7, hInterval, vInterval);// point[0] = x,point[1] = y,point[2] = winner
                    drawCircle(point[0], point[1], game.turn);
                    if (point[2] == 1)
                        ShowTheWinner();
                }
            }
            game.switchTurn();
        }
    }
    //drawing a circle on a given box
    @FXML
    void drawCircle(double x, double y, String turn){
        if(turn == "blue"){
            g.strokeOval(x, y,50, 50);
            g.setFill(Color.BLUE);
            g.fillOval(x, y, 50, 50);
        }
        else{
            g.strokeOval(x, y,50, 50);
            g.setFill(Color.RED);
            g.fillOval(x, y, 50, 50);
        }
    }
    //clear the board for a new game
    @FXML
    void Clear(ActionEvent event) {
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        initialize();
    }

}

