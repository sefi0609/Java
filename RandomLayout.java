package javafxapplication2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;


public class RandomLayout extends Application {

    @Override
    public void start(Stage primaryStage) {
        Random rand = new Random();
        Canvas c = new Canvas(350, 350);
        Button btn = new Button();
        btn.setText("New Layout");
        //  Create a Hbox for alignment
        HBox hb = new HBox();
        // Create a GridPane
        StackPane root = new StackPane();
        root.getChildren().add(c);
        hb.getChildren().add(btn);
        hb.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hb);
        GraphicsContext gc = c.getGraphicsContext2D() ;
        gc.setLineWidth(1.0);
        btn.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                gc.clearRect(0, 0, c.getWidth(), c.getHeight());
                for (double x = 0.5 ; x < c.getWidth(); x+=10) {//width
                    gc.moveTo(x, 0);
                    gc.lineTo(x, c.getHeight() -40);//height
                    gc.stroke();
                }
                for (double y = 0.5; y < c.getHeight() - 30; y+=10) {//height
                    gc.moveTo(0, y);
                    gc.lineTo(c.getWidth(), y);//width
                    gc.stroke();
                }
                // 10% of 1085 (31*35) is 108.5
                for (int i = 0;i < 108;i++){
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(310);
                    int temp = x % 10;
                    x = x - temp;
                    temp = y % 10;
                    y = y - temp;
                    gc.fillRect(x,y,10,10);
                }
            } 
        });
        primaryStage.setTitle("My Canvas");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
