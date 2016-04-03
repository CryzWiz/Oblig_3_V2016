package breakout;
import static breakout.BrickManager.*;

import javafx.scene.paint.Color;


public enum Level {
	ONE(1, 99, BRICK_COLORS_LVL1), TWO(2, 99, BRICK_COLORS_LVL2), THREE(3, 99, BRICK_COLORS_LVL3), VICTORY(4, 0,BRICK_COLORS_LVL1);

	private static Level level = ONE;
	int value;
	int removePercentage;
	Color[] brickColors;
    Color UNBREAKABLE_BRICK_COLOR = Color.GRAY;
    
	Level(int value, int removePercentage, Color[] brickColors) {
		this.value = value;
		this.removePercentage = removePercentage;
		this.brickColors = brickColors;
	}

	public static Level getLevel(){
		return level;
	}
	public static void nextLevel() {
		switch (level) {
		case ONE:
			level = TWO;
			break;
		case TWO:
			level = THREE;
			break;
		case THREE:
			level = VICTORY;
			break;
		default:
			level = ONE;
			break;
		}
	}

	public int value() {
		return value;
	}
	public int percentage() {
		return removePercentage;
	}
	public void brickColor(Brick brick, int row, int col) {

		switch (this) {
		case ONE:
			if (row < 3) {
				brick.setFill(brickColors[0]);
			} else if (row < 6) {
				brick.setFill(brickColors[1]);
			} else if (row < 9) {
				brick.setFill(brickColors[2]);
			} else {
				brick.setFill(brickColors[3]);
			}
			break;
			
		case TWO:
			if (col < BRICK_COLUMNS / 2) {
				if (row == col) {
					// Durable bricks
					brick.setFill(brickColors[0]);
					protectBricks(brick);
					brick.setDurability(DOUBLE_HIT);
				} else if (col - row == 1 || row - col == 1) {
					brick.setFill(brickColors[1]);
					protectBricks(brick);
				} else if (col - row == 2 || row - col == 2) {
					brick.setFill(brickColors[2]);
					protectBricks(brick);
				} else {
					brick.setFill(brickColors[3]);
				}
			} else {
				if (row + col == BRICK_COLUMNS - 1) {
					// Durable bricks
					brick.setFill(brickColors[0]);
					protectBricks(brick);
					brick.setDurability(DOUBLE_HIT);
				} else if (row + col == 13 || row + col == 15) {
					brick.setFill(brickColors[1]);
					protectBricks(brick);
				} else if (row + col == 12 || row + col == 16) {
					brick.setFill(brickColors[2]);
					protectBricks(brick);
				} else {
					brick.setFill(brickColors[3]);
				}
			}
			break;
			
		case THREE:
			// Unbreakable bricks:
			if(row == 0) {
				if(col < 6 || col > 8) {
					brick.setFill(UNBREAKABLE_BRICK_COLOR);
					setUnbreakableBrick(brick);
				} else {
					brick.setFill(brickColors[1]);
				}
			}
			else if(row == 9) {
				if(col < 10 && col > 4) {
					brick.setFill(UNBREAKABLE_BRICK_COLOR);
					setUnbreakableBrick(brick);
				} else {
					brick.setFill(brickColors[1]);
				}
			}
			// Pattern
			else if(row == 2 || row == 6) {
				if(col == 3 || col == 7 || col == 11) {
					brick.setFill(brickColors[0]);
					protectBricks(brick);
					brick.setDurability(DOUBLE_HIT);
				} else {
					brick.setFill(brickColors[1]);
				}
			} else if(row == 3 || row == 5) {
				
				if(col == 2 || col == 4 || col == 6 || col == 8 || col == 10 || col == 12) {
					// Durable bricks
					brick.setFill(brickColors[0]);
					protectBricks(brick);
					brick.setDurability(DOUBLE_HIT); 
				} else {
					brick.setFill(brickColors[1]);
				}
			} else if(row == 4) {
				if(col == 1 || col == 5 || col == 9 || col == 13) {
					// Durable bricks
					brick.setFill(brickColors[0]);
					protectBricks(brick);
					brick.setDurability(DOUBLE_HIT);
				} else {
					brick.setFill(brickColors[1]);
				}
			// Surrounding bricks
			} else {
				brick.setFill(brickColors[1]);
			}
			break;
			
		case VICTORY:
			reset();
			break;
		}

	}
	public static void reset(){
		level = ONE;
	}
};

