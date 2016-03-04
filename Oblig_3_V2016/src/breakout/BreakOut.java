package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BreakOut extends Application implements Settings{
  
  @Override 
  public void start(Stage primaryStage) {
    Pane pane = new Pane();
    Scene scene = new Scene(pane, WIDTH, HEIGHT);
    Room room = new Room(scene, BACKGROUND_COLOR);
    Ball ball = new Ball();
    Circle circle = ball.getCircle();
    Racket racket = new Racket();
    Rectangle pad = racket.getpad();
    
//    BrickManager manager = new BrickManager(BRICK_ROWS, BRICK_COLS); 
    BrickManager manager = new BrickManager(BRICK_ROWS, BRICK_COLS, Color.PALETURQUOISE, Color.GREENYELLOW, Color.GRAY, Color.RED);
    Brick[][] bricks = manager.getBricks();

	  for(int row = 0; row < BRICK_ROWS; row++){
		for(int col = 0; col < BRICK_COLS; col++){
			pane.getChildren().add(bricks[row][col].getRectangle());
		}
	  }

    pane.getChildren().add(pad);
    pane.getChildren().add(circle);
    
    scene.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent mouseEvent) {

	       racket.setpadX(mouseEvent.getX());
	       //System.out.println(racket.getpadX());

	       racket.setpadX(mouseEvent.getX());

	    }
	});

    EventHandler<ActionEvent> eventHandler = e -> {
    	for(Brick[] brickCol : bricks){
    		for(Brick brick : brickCol){
    			brick.collision(ball);
    		}
    	}
    	room.collision(ball);
    	ball.tick();
    };
    
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(MILLIS_PER_FRAME), eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
    
    primaryStage.setTitle("BreakOut"); 
    primaryStage.setResizable(false);
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
