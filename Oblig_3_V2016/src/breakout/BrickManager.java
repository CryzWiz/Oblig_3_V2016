package breakout;

import static breakout.Level.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	private Brick[][] bricks;
	private int numberOfRows;
	private int numberOfCols;
	private int bricksLeft;

	private Level currentLevel;

	public BrickManager(Pane pane) {
		this(pane, BRICK_ROWS, BRICK_COLUMNS);
	}

	public BrickManager(Pane pane, int numberOfRows, int numberOfCols) {
		currentLevel = ONE;
		createBricks(pane, numberOfRows, numberOfCols);
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		bricksLeft = numberOfRows * numberOfCols;
	}

	public void setLevel(Level lvl) {// -------------------------------------------------
		currentLevel = lvl;
		reset();
	}

	public void createBricks(Pane pane, int numberOfRows, int numberOfCols) {
		bricks = new Brick[numberOfRows][numberOfCols];
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {
				Brick brick = new Brick(setX(col), setY(row), BRICK_WIDTH, BRICK_HEIGHT);
				bricks[row][col] = brick;
				pane.getChildren().add(brick.getRectangle());
				currentLevel.brickColor(brick, row, col);

			}
		}
	}

	public int setX(int col) {
		return col * (BRICK_WIDTH + BRICK_PADDING_H) + WALL_PADDING_LEFT;
	}

	public int setY(int row) {
		return row * (BRICK_HEIGHT + BRICK_PADDING_V) + WALL_PADDING_TOP;
	}

/*	public void setBrickColor(Brick brick, int row, int col) {
		switch (currentLevel) {
		case ONE:
			if (row < 3) {
				brick.setFill(currentLevel.brickColors[0]);
			} else if (row < 6) {
				brick.setFill(currentLevel.brickColors[1]);
			} else if (row < 9) {
				brick.setFill(currentLevel.brickColors[2]);
			} else {
				brick.setFill(currentLevel.brickColors[3]);
			}
			break;
		case TWO:
			if (col < BRICK_COLUMNS / 2) {
				if (row == col) {
					brick.setFill(currentLevel.brickColors[0]);
					brick.setProtection(true);
				} else if (col - row == 1 || row - col == 1) {
					brick.setFill(currentLevel.brickColors[1]);
					brick.setProtection(true);
				} else if (col - row == 2 || row - col == 2) {
					brick.setFill(currentLevel.brickColors[2]);
					brick.setProtection(true);
				} else {
					brick.setFill(currentLevel.brickColors[3]);
				}
			} else {
				if (row + col == BRICK_COLUMNS - 1) {
					brick.setFill(currentLevel.brickColors[0]);
					brick.setProtection(true);
				} else if (row + col == 13 || row + col == 15) {
					brick.setFill(currentLevel.brickColors[1]);
					brick.setProtection(true);
				} else if (row + col == 12 || row + col == 16) {
					brick.setFill(currentLevel.brickColors[2]);
					brick.setProtection(true);
				} else {
					brick.setFill(currentLevel.brickColors[3]);
				}
			}
		case THREE:
			break;
		case VICTORY:
			break;
		}
	}*/

	/*
	 * public void setBrickColors(Color[] brickColors) { switch (currentLevel) {
	 * case ONE: for (int row = 0; row < bricks.length; row++) { for (int col =
	 * 0; col < bricks[row].length; col++) { if (row < 3) {
	 * bricks[row][col].setFill(brickColors[0]); } else if (row < 6) {
	 * bricks[row][col].setFill(brickColors[1]); } else if (row < 9) {
	 * bricks[row][col].setFill(brickColors[2]); } else {
	 * bricks[row][col].setFill(brickColors[3]); }
	 * 
	 * } } break; case TWO: // Diagonal from upper left down to middle
	 * (inclusive) for(int row = 0; row < BRICK_ROWS; row ++) { for(int col = 0;
	 * col <= BRICK_COLUMNS / 2; col++) { if(row == col) {
	 * bricks[row][col].setFill(brickColors[0]);
	 * bricks[row][col].setProtection(true); } else if(col - row == 1 || row -
	 * col == 1) { bricks[row][col].setFill(brickColors[1]);
	 * bricks[row][col].setProtection(true); } else if(col - row == 2 || row -
	 * col == 2) { bricks[row][col].setFill(brickColors[2]);
	 * bricks[row][col].setProtection(true); } else {
	 * bricks[row][col].setFill(brickColors[3]); } } } // Diagonal from upper
	 * right down to middle (exclusive) for(int row = 0; row < BRICK_ROWS;
	 * row++) { for(int col = 8; col < BRICK_COLUMNS; col++) { if(row + col ==
	 * BRICK_COLUMNS - 1) { bricks[row][col].setFill(brickColors[0]);
	 * bricks[row][col].setProtection(true); } else if(row + col == 13 || row +
	 * col == 15) { bricks[row][col].setFill(brickColors[1]);
	 * bricks[row][col].setProtection(true); } else if(row + col == 12 || row +
	 * col == 16) { bricks[row][col].setFill(brickColors[2]);
	 * bricks[row][col].setProtection(true); } else {
	 * bricks[row][col].setFill(brickColors[3]); } } } break; case THREE: break;
	 * case VICTORY: break; }
	 * 
	 * }
	 */

	public void destroyRandomBricks(int percentBricksToRemove) {
		int numberOfBricksToRemove = (int) (((numberOfRows * numberOfCols) / 100.0) * percentBricksToRemove);

		while (numberOfBricksToRemove > 0) {
			int randomRow = (int) (Math.random() * numberOfRows);
			int randomCol = (int) (Math.random() * numberOfCols);
			if (!bricks[randomRow][randomCol].isDestroyed() && !bricks[randomRow][randomCol].isProtected()) {
				bricks[randomRow][randomCol].destroy();
				numberOfBricksToRemove--;
				bricksLeft--;
			}
		}
	}

	public void reset() {
		for (Brick[] col : bricks) {
			for (Brick brick : col) {
				brick.reset();
				}
		}
		bricksLeft = numberOfRows * numberOfCols;
		// setLevel(currentLevel);
		destroyRandomBricks(currentLevel.percentage());
	}

	public Brick getBrick(int row, int col) {
		return bricks[row][col];
	}

	public Brick[][] getBricks() {
		return bricks;
	}

	public void collision(Ball ball) {
		int firstCol = getColumn(ball.getBoundsLeft());
		int lastCol = getColumn(ball.getBoundsRight());
		int firstRow = getRow(ball.getBoundsTop());
		int lastRow = getRow(ball.getBoundsBottom());

		if (lastRow >= 0 && firstRow < BRICK_ROWS && lastCol >= 0 && firstCol < BRICK_COLUMNS) {
			firstRow = Math.max(firstRow, 0);
			lastRow = Math.min(lastRow, BRICK_ROWS - 1);
			firstCol = Math.max(firstCol, 0);
			lastCol = Math.min(lastCol, BRICK_COLUMNS - 1);

			for (int row = firstRow; row <= lastRow; row++) {
				for (int col = firstCol; col <= lastCol; col++) {
					Brick brick = bricks[row][col];
					if (!brick.isDestroyed()) {
						brick.collision(ball);
						if (brick.isDestroyed())
							bricksLeft--;
					}
				}
			}
		}
	}

	public int getRow(double y) {
		return (int) ((y - WALL_PADDING_TOP) / (BRICK_HEIGHT + BRICK_PADDING_V));
	}

	public int getColumn(double x) {
		return (int) ((x - WALL_PADDING_LEFT) / (BRICK_WIDTH + BRICK_PADDING_H));
	}

	public boolean levelComplete() {
		return ((bricksLeft > 0) ? false : true);
	}

	public void tick(GameManager gManager, Ball ball) {
		collision(ball);
		if (levelComplete()) {
			gManager.victory();
			setLevel(currentLevel.nextLevel());
		}
	}
}
