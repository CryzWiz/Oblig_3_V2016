package breakout;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public interface Settings {
	Color BACKGROUND_COLOR = Color.BLACK;
	Color BALL_COLOR = Color.YELLOWGREEN;
	Color PADDLE_COLOR = Color.GRAY;
	Color TEXT_COLOR_GAMEOVER = Color.GREEN;

	Background BACKGROUND = new Background(new BackgroundFill(BACKGROUND_COLOR, null, null));
	/*Color BRICK_COLOR_LAYER1 = Color.BLUE;
      Color BRICK_COLOR_LAYER2 = Color.GREEN;
      Color BRICK_COLOR_LAYER3 = Color.YELLOW;
      Color BRICK_COLOR_LAYER4 = Color.RED;*/

	Color BRICK_COLOR_LAYER1 = Color.PALETURQUOISE;
	Color BRICK_COLOR_LAYER2 = Color.GREENYELLOW;
	Color BRICK_COLOR_LAYER3 = Color.GRAY;
	Color BRICK_COLOR_LAYER4 = Color.RED;

	boolean BOUNCE_ON_BOT = false;

	int MILLIS_PER_FRAME =10; //Lower speed + higher framerate = same look, more precision, higher CPU load

	int FONT_SIZE_GAMEOVER = 48;

	int BALL_START_X = 400;
	int BALL_START_Y = 500;
	int BALL_START_DX = 3;
	int BALL_START_DY = -3;
	int BALL_RADIUS = 10;
	
	int PADDLE_HEIGHT = 10;
	int PADDLE_WIDTH = 200;
	int PADDLE_Y_OFFSET = BALL_START_Y;
	int PADDLE_X_OFFSET = BALL_START_X - (PADDLE_WIDTH / 2);

	int BRICK_WIDTH = 60;
	int BRICK_HEIGHT = 20;
	int BRICK_PADDING_H = 1;
	int BRICK_PADDING_V = 3;
	int BRICK_ROWS = 10;
	int BRICK_COLS = 15;

	int WALL_PADDING_LEFT = BRICK_WIDTH;
	int WALL_PADDING_RIGHT = BRICK_WIDTH;
	int WALL_PADDING_TOP = BRICK_WIDTH;
	int WALL_PADDING_BOTTOM = BRICK_WIDTH + 200;

	int WIDTH = //1021
			WALL_PADDING_LEFT + WALL_PADDING_RIGHT +
			BRICK_COLS * (BRICK_WIDTH + BRICK_PADDING_H) - BRICK_PADDING_H;
	int HEIGHT = //581
			WALL_PADDING_TOP + WALL_PADDING_BOTTOM +
			BRICK_ROWS * (BRICK_HEIGHT + BRICK_PADDING_V) - BRICK_PADDING_V;
	
	int PERCENT_BRICKS_TO_REMOVE = 20;
	int TIMER_X = WIDTH / 2; //--------------------------------------------------- CHANGE THIS!!!!!
	int TIMER_Y = HEIGHT / 2;     //--------------------------------------------------- CHANGE THIS!!!!!
}
