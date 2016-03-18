package breakout;
import static breakout.BrickManager.*;

import javafx.scene.paint.Color;


public enum Level {
	ONE(1, 99, BRICK_COLORS_LVL1), TWO(2, 15, BRICK_COLORS_LVL2), THREE(3, 10, BRICK_COLORS_LVL3), VICTORY(4, 0,BRICK_COLORS_LVL1);

	private static Level level = ONE;
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

	int value;
	int removePercentage;
	Color[] brickColors;

	Level(int value, int removePercentage, Color[] brickColors) {
		this.value = value;
		this.removePercentage = removePercentage;
		this.brickColors = brickColors;
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
					brick.setFill(brickColors[0]);
					brick.setProtection(true);
				} else if (col - row == 1 || row - col == 1) {
					brick.setFill(brickColors[1]);
					brick.setProtection(true);
				} else if (col - row == 2 || row - col == 2) {
					brick.setFill(brickColors[2]);
					brick.setProtection(true);
				} else {
					brick.setFill(brickColors[3]);
				}
			} else {
				if (row + col == BRICK_COLUMNS - 1) {
					brick.setFill(brickColors[0]);
					brick.setProtection(true);
				} else if (row + col == 13 || row + col == 15) {
					brick.setFill(brickColors[1]);
					brick.setProtection(true);
				} else if (row + col == 12 || row + col == 16) {
					brick.setFill(brickColors[2]);
					brick.setProtection(true);
				} else {
					brick.setFill(brickColors[3]);
				}
			}
			break;
		case THREE:
			// Diagonal from upper left down to middle (inclusive)
			if (col < BRICK_COLUMNS / 2) {
				if(row == col) {
					brick.setFill(brickColors[0]);
					brick.setProtection(true);
				} else if(col - row == 1 || col - row == 2) {
					brick.setFill(brickColors[1]);
					brick.setProtection(true);
				} else if(col - row == 3 || col - row == 4) {
					brick.setFill(brickColors[2]);
					brick.setProtection(true);
				} else {
					brick.setFill(brickColors[3]);
				}
			} else {
			// Diagonal from upper right down to middle (exclusive)
				if(row + col == BRICK_COLUMNS - 1) {
					brick.setFill(brickColors[0]);
					brick.setProtection(true);
				} else if(row + col == 14 || row + col == 13) {
					brick.setFill(brickColors[1]);
					brick.setProtection(true);
				} else if(row + col == 12 || row + col == 11) {
					brick.setFill(brickColors[2]);
					brick.setProtection(true);
				} else {
					brick.setFill(brickColors[3]);
				}
			}
			break;
		case VICTORY:
			break;
		}

	}
};

