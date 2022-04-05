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
    private int numOfShapes;//counter for number of shapes drawn for the undo button
    private double x;//new x coordinates every drag
    private double y;//new y coordinates every drag

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
    //get the new x,y every mouse drag and every time for effect
    @FXML
    void newCoordinates(MouseEvent event){
         if(selectedShape != null) {
                 x = event.getX();
                 y = event.getY();
                 draw();
         }
    }
    //initialize function for the stat of the application
    @FXML
    public void initialize() {
        selectedShape = null;
        selectedColor = Color.WHITE;
        mousePressed = new double[2];
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
        numOfShapes += 1;//count every mouse released
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
        }
    }
    //drawing a line
    void drawALine(){
        clearForVision();//clear the pane every time there is new x,y for effect
        Line line = new Line(mousePressed[0],mousePressed[1],x,y);
        if(fill)//if the check box is clicked the line will be in the color selected by the user
            line.setStroke(selectedColor);
        pane.getChildren().add(line);
    }
    //drawing a circle
    void drawACircle(){
        clearForVision();
        //adjusting the location of the circle and the radius
        double radius = Math.hypot((mousePressed[0] - x),(mousePressed[1] - y));
        double _x = (mousePressed[0] + x)/2;
        double _y = (mousePressed[1] + y)/2;
        Circle circle = new Circle(_x,_y,(radius/2));
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
        clearForVision();
        Rectangle r = new Rectangle();
        if(mousePressed[0] < x) {//adjusting the right left corner of the rectangle
            if(mousePressed[1] < y) {
                r.setX(mousePressed[0]);
                r.setY(mousePressed[1]);
            }
            else{//mousePressed[1] > y
                r.setX(mousePressed[0]);
                r.setY(y);
            }
        }
        else{
            if(mousePressed[1] < y) {
                r.setX(x);
                r.setY(mousePressed[1]);
            }
            else{//mousePressed[1] > y
                r.setX(x);
                r.setY(y);
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
        r.setWidth(Math.abs(x - mousePressed[0]));
        r.setHeight(Math.abs(mousePressed[1] - y));
        pane.getChildren().add(r);
    }
    void clearForVision(){
        try {
            pane.getChildren().remove(numOfShapes);
        } catch (Exception e){
            //IndexOutOfBoundsException
        }
    }

}
