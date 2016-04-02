package breakout;

import static breakout.Level.*;

import breakout.Brick.COLLISION_TYPE;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	private Brick[][] bricks;
	private int numberOfRows;
	private int numberOfCols;
	private int bricksLeft;
	private static int unbreakableBricks;

	public BrickManager(Pane pane) {
		this(pane, BRICK_ROWS, BRICK_COLUMNS);
	}
	public BrickManager(Pane pane, int numberOfRows, int numberOfCols) {
		unbreakableBricks = 0;
		bricksLeft = numberOfRows * numberOfCols - unbreakableBricks;
		createBricks(pane, numberOfRows, numberOfCols);
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		
	}

	public void createBricks(Pane pane, int numberOfRows, int numberOfCols) {
		bricks = new Brick[numberOfRows][numberOfCols];
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {
				Brick brick = new Brick(setX(col), setY(row), BRICK_WIDTH, BRICK_HEIGHT);
				bricks[row][col] = brick;
				pane.getChildren().add(brick.getRectangle());
				getLevel().brickColor(brick, row, col);
				if(col > 0)
					bricks[row][col-1].setCloseBrick(0, brick);
				if(row > 0)
					bricks[row-1][col].setCloseBrick(1, brick);
			}
		}
	}
	public void resetBrickColors() {
		for(int row = 0; row < BRICK_ROWS; row++){
			for(int col = 0; col < BRICK_COLUMNS; col++){
				bricks[row][col].reset();
				getLevel().brickColor(bricks[row][col], row, col);
			}
		}
	}
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
	public static void setUnbreakableBrick(Brick brick) {
		brick.setFill(Color.GRAY);
		brick.setProtection(true);
		brick.setUnbreakable(true);
		unbreakableBricks++;
	}
	public void reset() {
//		resetBrickColors();
		bricksLeft = numberOfRows * numberOfCols - unbreakableBricks;
		destroyRandomBricks(getLevel().percentage());
	}

	public Brick[][] getBricks() {
		return bricks;
	}
	public Brick getBrick(int row, int col) {
		return bricks[row][col];
	}

	public int getRow(double y) {
		return (int) ((y - WALL_PADDING_TOP) / (BRICK_HEIGHT + BRICK_PADDING_V));
	}
	public int getColumn(double x) {
		return (int) ((x - WALL_PADDING_LEFT) / (BRICK_WIDTH + BRICK_PADDING_H));
	}
	public int setX(int col) {
		return col * (BRICK_WIDTH + BRICK_PADDING_H) + WALL_PADDING_LEFT;
	}
	public int setY(int row) {
		return row * (BRICK_HEIGHT + BRICK_PADDING_V) + WALL_PADDING_TOP;
	}

	public boolean levelCleared() {
		return ((bricksLeft > 0) ? false : true);
	}
	public void setNextLevel() {
		nextLevel();
		resetBrickColors();
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
						COLLISION_TYPE type = brick.collision(ball);
						if (brick.isDestroyed())
							bricksLeft--;
						if(type == COLLISION_TYPE.EDGE_DOUBLE)
							break;
					}
				}
			}
		}
	}
	public void tick(GameManager gManager, Ball ball) {
		collision(ball);
		if (levelCleared()) {
			setNextLevel();
			gManager.levelCleared();
		}
	}

}
