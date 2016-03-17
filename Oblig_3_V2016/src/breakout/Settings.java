package breakout;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public interface Settings {
	boolean BALL_GRAVITY_ON = true;
	boolean BOUNCE_ON_BOT = false;
	boolean FRICTION_ON = false;
	boolean ALLOW_TILT = true;

	int MILLIS_PER_FRAME = 20; //Lower speed + higher frame rate = same look, more precision, higher CPU load

	int COLLISION_MODEL_OLD = 0;
	int COLLISION_MODEL_SIMPLE = 1;
	int COLLISION_MODEL_FULL = 2;
	int COLLISION_MODEL_DEFAULT = COLLISION_MODEL_FULL;

	Font FONT_TEXT = Font.font(48);
	Font FONT_TIMER = Font.font(30);
	
	Color BRICK_COLOR_LAYER1 = Color.BLUE;
    Color BRICK_COLOR_LAYER2 = Color.GREEN;
    Color BRICK_COLOR_LAYER3 = Color.YELLOW;
    Color BRICK_COLOR_LAYER4 = Color.RED;
//	Color BRICK_COLOR_LAYER1 = Color.PALETURQUOISE;
//	Color BRICK_COLOR_LAYER2 = Color.GREENYELLOW;
//	Color BRICK_COLOR_LAYER3 = Color.GRAY;
//	Color BRICK_COLOR_LAYER4 = Color.RED;
    
//    Color[] BRICK_COLORS_LVL1 = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
    Color[] BRICK_COLORS_LVL1 = {Color.PALETURQUOISE, Color.GREEN, Color.YELLOW, Color.RED};
    Color[] BRICK_COLORS_LVL2 = {Color.BLUE, Color.ALICEBLUE, Color.CORNFLOWERBLUE, Color.PALETURQUOISE};

    Color BACKGROUND_COLOR = Color.BLACK;
	Color BALL_COLOR = Color.YELLOWGREEN;
	Color PADDLE_COLOR = Color.GRAY;
	Color TEXT_COLOR = Color.GREEN;
	Background BACKGROUND = new Background(new BackgroundFill(BACKGROUND_COLOR, null, null));

	int PADDLE_HEIGHT = 10;
	int PADDLE_WIDTH = 200;
	int PADDLE_Y_OFFSET = 500;
	int PADDLE_X_OFFSET = 300;
	double PADDLE_FRICTION_FACTOR = 0.6;
	double PADDLE_TILT_ANGLE = Math.PI / 12;

	int BALL_RADIUS = 10;
	int BALL_START_X = PADDLE_X_OFFSET + PADDLE_WIDTH / 2;
	int BALL_START_Y = PADDLE_Y_OFFSET - BALL_RADIUS;
	int BALL_START_DX = 10;
	int BALL_START_DY = -6;
	int BALL_GRAVITY_THRESHOLD = 1;
	double BALL_GRAVITY_FACTOR = (Math.abs(BALL_START_DY) + Math.abs(BALL_START_DX)) / 5.0;
	
	int BRICK_WIDTH = 60;
	int BRICK_HEIGHT = 20;
	int BRICK_PADDING_H = 1;
	int BRICK_PADDING_V = 3;
	int BRICK_ROWS = 10;
	int BRICK_COLUMNS = 15;

	int WALL_PADDING_LEFT = BRICK_WIDTH;
	int WALL_PADDING_RIGHT = BRICK_WIDTH;
	int WALL_PADDING_TOP = BRICK_WIDTH; //Or BRICK_HEIGHT???
	int WALL_PADDING_BOTTOM = BRICK_WIDTH + 200;

	int WIDTH = //1021
			WALL_PADDING_LEFT + WALL_PADDING_RIGHT +
			BRICK_COLUMNS * (BRICK_WIDTH + BRICK_PADDING_H) - BRICK_PADDING_H;
	int HEIGHT = //581
			WALL_PADDING_TOP + WALL_PADDING_BOTTOM +
			BRICK_ROWS * (BRICK_HEIGHT + BRICK_PADDING_V) - BRICK_PADDING_V;
	
	//int PERCENT_BRICKS_TO_REMOVE = 99;
}
