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
    
    Brick[][] bricks = new Brick[BRICK_ROWS][BRICK_COLS];
    for(int y = 0; y < BRICK_ROWS; y++){
    	for(int x = 0; x < BRICK_COLS; x++){
    		Brick brick = new Brick(
    				x * (BRICK_WIDTH + BRICK_PADDING_H) + WALL_PADDING_LEFT,
    				y * (BRICK_HEIGHT + BRICK_PADDING_V) + WALL_PADDING_TOP,
    				BRICK_WIDTH,
    				BRICK_HEIGHT);
    		//rect.setFill(BRICK_COLOR);
    		pane.getChildren().add(brick.getRectangle());
    		bricks[y][x]= brick;
    		if(y < 3){
	    		bricks[y][x].setFill(Color.BLUE);
	    	}
	    	else if(y < 6){
	    		bricks[y][x].setFill(Color.GREEN);
	    	}
	    	else if(y < 9){
	    		bricks[y][x].setFill(Color.YELLOW);
	    	}
	    	else{
	    		bricks[y][x].setFill(Color.RED);
	    	}
    	}
    }
    Rectangle pad = new Rectangle(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
    pad.setFill(Color.GRAY);
    
    pane.getChildren().add(pad);
    pane.getChildren().add(circle);
    
    /*EventHandler<MouseEvent> mouseHandler = m -> {
    	racket.mouse(m.getSceneX());
    };*/

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
