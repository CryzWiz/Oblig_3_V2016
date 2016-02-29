package breakout;

import javafx.scene.paint.Color;

public interface Settings {
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
	  public static final int BRICK_ROWS = 10;
	  public static final int BRICK_COLS = 15;
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
	  public static final int PADDLE_Y_OFFSET = BALL_START_Y;
	  public static final int PADDLE_X_OFFSET = BALL_START_X;
	  public static final int PADDLE_HEIGHT = 10;
	  public static final int PADDLE_WIDTH = 200;
}
