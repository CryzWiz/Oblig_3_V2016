package breakout;

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

public class BreakOut extends Application{
  //Make an interface for the constants?
  public static final Color BACKGROUND_COLOR = Color.BLACK;
  public static final Color BRICK_COLOR = Color.DARKCYAN;
  public static final Color BALL_COLOR = Color.YELLOWGREEN;
  public static final int MILLIS_PER_FRAME = 40;
  public static final int BALL_START_X = 400;
  public static final int BALL_START_Y = 500;
  public static final int BALL_START_DX = 13;
  public static final int BALL_START_DY = -11;
  public static final int BALL_RADIUS = 10;
  public static final int BRICK_WIDTH = 100;
  public static final int BRICK_HEIGHT = 20;
  public static final int BRICK_PADDING_H = 3;
  public static final int BRICK_PADDING_V = 3;
  public static final int BRICK_ROWS = 8;
  public static final int BRICK_COLS = 8;
  public static final int WALL_PADDING_LEFT = BRICK_WIDTH;
  public static final int WALL_PADDING_RIGHT = BRICK_WIDTH;
  public static final int WALL_PADDING_TOP = BRICK_WIDTH;
  public static final int WALL_PADDING_BOTTOM = BRICK_WIDTH + 200;
  public static final int WIDTH = //1021
		  WALL_PADDING_LEFT + WALL_PADDING_RIGHT +
		  BRICK_COLS * (BRICK_WIDTH + BRICK_PADDING_H) - BRICK_PADDING_H;
  public static final int HEIGHT = //581
		  WALL_PADDING_TOP + WALL_PADDING_BOTTOM +
		  BRICK_ROWS * (BRICK_HEIGHT + BRICK_PADDING_V) - BRICK_PADDING_V;
  //public static final int WIDTH = 1000;
  //public static final int HEIGHT = 600;
  
  @Override 
  public void start(Stage primaryStage) {
    Pane pane = new Pane();
    Scene scene = new Scene(pane, WIDTH, HEIGHT);
    Room room = new Room(scene, BACKGROUND_COLOR);
    Ball ball = new Ball();
    Circle circle = ball.getCircle();
    
    Rectangle[][] rectangles = new Rectangle[8][8];
    for(int y = 0; y < BRICK_ROWS; y++){
    	for(int x = 0; x < BRICK_COLS; x++){
    		Rectangle rect = new Rectangle(
    				x * (BRICK_WIDTH + BRICK_PADDING_H) + WALL_PADDING_LEFT,
    				y * (BRICK_HEIGHT + BRICK_PADDING_V) + WALL_PADDING_TOP,
    				BRICK_WIDTH,
    				BRICK_HEIGHT);
    		rect.setFill(BRICK_COLOR);
    		pane.getChildren().add(rect);
    		rectangles[y][x]= rect;
    	}
    }

    pane.getChildren().add(circle);

    EventHandler<ActionEvent> evenHandler = e -> {
    	room.collision(ball);
    	ball.tick();
    };
    
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(MILLIS_PER_FRAME), evenHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
    
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
