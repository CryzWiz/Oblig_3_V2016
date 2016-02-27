
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;

public class BreakOut extends Application{
  @Override 
  public void start(Stage primaryStage) {
    Pane pane = new Pane();

    Rectangle[][] rectangles = new Rectangle[8][8];
    for(int y = 0; y < 8; y++){
    	for(int x = 0; x < 8; x++){
    		rectangles[y][x]= new Rectangle(x*(100+3), y*(20+3), 100, 20);
    		pane.getChildren().add(rectangles[y][x]);
    	}
    }
    
    Circle circle = new Circle(400, 500, 20);
    circle.setFill(Color.BLUE);
    pane.getChildren().add(circle);
    
    EventHandler<ActionEvent> evenHandler = e -> {
    	circle.setTranslateX(circle.getTranslateX()+5);
    	circle.setTranslateY(circle.getTranslateY()-3);
    };
    
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(40), evenHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
    
    Scene scene = new Scene(pane, 950, 600);
    scene.setFill(Color.RED);
    primaryStage.setTitle("BreakOut"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }




   /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
   public static void main(String[] args) {
   Application.launch(args);
   }

}
