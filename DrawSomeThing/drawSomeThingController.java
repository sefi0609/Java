package com.example.drawsomething;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class drawSomeThingController {

    private String selectedShape;//contain the selected shape
    private Color selectedColor;//contain the selected color
    private boolean fill;//indicates if the check box is clicked
    private double[] mousePressed;//indicates the x,y coordinates when the mouse is clicked, mousePressed[0] = x, mousePressed[1] = y
    private double[] mouseReleased;//indicates the x,y coordinates when the mouse is released, mousePressed[0] = x, mousePressed[1] = y
    private int numOfShapes;//counter for number of shapes drawn for the undo button

    //variables for the application controllers
    @FXML
    private Button clearButton;

    @FXML
    private ColorPicker dropDownC;

    @FXML
    private ComboBox dropDownS;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button undoButton;
    @FXML
    private Pane pane;
    //initialize function for the stat of the application
    @FXML
    public void initialize() {
        selectedShape = null;
        selectedColor = Color.WHITE;
        mousePressed = new double[2];
        mouseReleased = new double[2];
        numOfShapes = 0;
        dropDownS.getItems().addAll("Line", "Circle", "Rectangle");
    }
    //getting the x,y coordinates when the mouse is pressed
    @FXML
    void mousePressed(MouseEvent event) {
        mousePressed[0] = event.getX();
        mousePressed[1] = event.getY();
    }
    //getting the x,y coordinates when the mouse is released
    @FXML
    void mouseReleased(MouseEvent event) {
        mouseReleased[0] = event.getX();
        mouseReleased[1] = event.getY();
        draw();
    }
    //clear the pane and zero the counter for number of shapes
    @FXML
    void clear(ActionEvent event) {
        pane.getChildren().clear();
        numOfShapes = 0;
    }
    //getting the selected color from the user
    @FXML
    void clickForC(ActionEvent event) {selectedColor = dropDownC.getValue();}
    //getting the selected shape from the user
    @FXML
    void clickForS(ActionEvent event) {selectedShape = (String) dropDownS.getValue();}
    //getting if the check box is clicked or not
    @FXML
    void fillOrNot(ActionEvent event) {fill = checkBox.isSelected();}
    //removing the last shape drawn
    @FXML
    void undo(ActionEvent event) {
        if(numOfShapes > 0) {
            pane.getChildren().remove((numOfShapes - 1));
            numOfShapes -= 1;
        }
    }
    //general function for drawing shapes
    void draw(){
        if(selectedShape != null) {
            switch (selectedShape) {
                case "Rectangle":
                    drawARectangle();
                    break;
                case "Circle":
                    drawACircle();
                    break;
                case "Line":
                    drawALine();
                    break;
            }
            numOfShapes += 1;
        }
    }
    //drawing a line
    void drawALine(){
        Line line = new Line(mousePressed[0],mousePressed[1],mouseReleased[0],mouseReleased[1]);
        if(fill)//if the check box is clicked the line will be in the color selected by the user
            line.setStroke(selectedColor);
        pane.getChildren().add(line);
    }
    //drawing a circle
    void drawACircle(){
        //adjusting the location of the circle and the radius
        double radius = Math.hypot((mousePressed[0] - mouseReleased[0]),(mousePressed[1] - mouseReleased[1]));
        double x = (mousePressed[0] + mouseReleased[0])/2;
        double y = (mousePressed[1] + mouseReleased[1])/2;
        Circle circle = new Circle(x,y,(radius/2));
        if(fill) {//if the check box is clicked the circle will be in the color selected by the user
            circle.setFill(selectedColor);
            circle.setStrokeWidth(1);
            circle.setStroke(Color.BLACK);
        }
        else{//if the check box is not clicked the circle will be empty
            circle.setFill(Color.TRANSPARENT);
            circle.setStrokeWidth(1);
            circle.setStroke(Color.BLACK);
        }
        pane.getChildren().add(circle);
    }
    //drawing a rectangle
    void drawARectangle(){
        Rectangle r = new Rectangle();
        if(mousePressed[0] < mouseReleased[0]) {//adjusting the right left corner of the rectangle
            if(mousePressed[1] < mouseReleased[1]) {
                r.setX(mousePressed[0]);
                r.setY(mousePressed[1]);
            }
            else{//mousePressed[1] > mouseReleased[1]
                r.setX(mousePressed[0]);
                r.setY(mouseReleased[1]);
            }
        }
        else{
            if(mousePressed[1] < mouseReleased[1]) {
                r.setX(mouseReleased[0]);
                r.setY(mousePressed[1]);
            }
            else{//mousePressed[1] > mouseReleased[1]
                r.setX(mouseReleased[0]);
                r.setY(mouseReleased[1]);
            }
        }
        if(fill) {//if the check box is clicked the rectangle will be in the color selected by the user
            r.setFill(selectedColor);
            r.setStrokeWidth(1);
            r.setStroke(Color.BLACK);
        }
        else {//if the check box is not clicked the rectangle will be empty
            r.setFill(Color.TRANSPARENT);
            r.setStrokeWidth(1);
            r.setStroke(Color.BLACK);
        }
        //adjusting the width and the height of the rectangle
        r.setWidth(Math.abs(mouseReleased[0] - mousePressed[0]));
        r.setHeight(Math.abs(mousePressed[1] - mouseReleased[1]));
        pane.getChildren().add(r);
    }

}
