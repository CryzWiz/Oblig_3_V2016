package breakout;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	private Brick[][] bricks;
	private int numberOfRows;
	private int numberOfCols;
	private int bricksLeft;
	private int currentLevel;
	

	public BrickManager(Pane pane) { 
		this(pane, BRICK_ROWS, BRICK_COLS);
	}

	public BrickManager(Pane pane, int numberOfRows, int numberOfCols) { 		
		createBricks(numberOfRows, numberOfCols);
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		bricksLeft = numberOfRows * numberOfCols;
		setLevel(1); //-------------------------------------------------
		for(int row = 0; row < BRICK_ROWS; row++){
			for(int col = 0; col < BRICK_COLS; col++){
				pane.getChildren().add(bricks[row][col].getRectangle());
			}
		}
	}
	
	public void setLevel(int lvl) {//-------------------------------------------------
		currentLevel = lvl;
		switch(currentLevel) {
			case 1:
				setBrickColors(BRICK_COLORS_LVL1);
				destroyRandomBricks(PERCENT_BRICKS_TO_REMOVE);
				break;
			case 2:
				setBrickColors(BRICK_COLORS_LVL2);
				destroyRandomBricks(PERCENT_BRICKS_TO_REMOVE / 2);
				break;
			case 3:
				break;
		
		}
	}


	public void createBricks(int numberOfRows, int numberOfCols) {
		Brick[][] bricks = new Brick[numberOfRows][numberOfCols];
		for(int row = 0; row < numberOfRows; row++){
			for(int col = 0; col < numberOfCols; col++){
				Brick brick = new Brick(
						col * (BRICK_WIDTH + BRICK_PADDING_H) + WALL_PADDING_LEFT,
						row * (BRICK_HEIGHT + BRICK_PADDING_V) + WALL_PADDING_TOP,
						BRICK_WIDTH,
						BRICK_HEIGHT);

				bricks[row][col]= brick;
			}

		}
		this.bricks = bricks;
	}

	public void setBrickColors(Color[] brickColors) {
		switch (currentLevel) {
		case 1:
			for (int row = 0; row < bricks.length; row++) {
				for (int col = 0; col < bricks[row].length; col++) {
					if (row < 3) {
						bricks[row][col].setFill(brickColors[0]);
					} else if (row < 6) {
						bricks[row][col].setFill(brickColors[1]);
					} else if (row < 9) {
						bricks[row][col].setFill(brickColors[2]);
					} else {
						bricks[row][col].setFill(brickColors[3]);
					}

				}
			}
			break;
		case 2:
			// Diagonal from upper left down to middle (inclusive)
			for(int row = 0; row < BRICK_ROWS; row ++) {
				for(int col = 0; col <= BRICK_COLS / 2; col++) {
					if(row == col) {
						bricks[row][col].setFill(brickColors[0]);
						bricks[row][col].setProtection(true);
					} else if(col - row == 1 || row - col == 1) {
						bricks[row][col].setFill(brickColors[1]);
						bricks[row][col].setProtection(true);
					} else if(col - row == 2 || row - col == 2) {
						bricks[row][col].setFill(brickColors[2]);
						bricks[row][col].setProtection(true);
					} else {
						bricks[row][col].setFill(brickColors[3]);
					}
				}
			}
			// Diagonal from upper right down to middle (exclusive)
			for(int row = 0; row < BRICK_ROWS; row++) {
				for(int col = 8; col < BRICK_COLS; col++) {
					if(row + col == BRICK_COLS - 1) {
						bricks[row][col].setFill(brickColors[0]);
						bricks[row][col].setProtection(true);
					} else if(row + col == 13 || row + col == 15) {
						bricks[row][col].setFill(brickColors[1]);
						bricks[row][col].setProtection(true);
					} else if(row + col == 12 || row + col == 16) {
						bricks[row][col].setFill(brickColors[2]);
						bricks[row][col].setProtection(true);
					} else {
						bricks[row][col].setFill(brickColors[3]);
					}
				}
			}
			break;
		case 3:
			break;
		}

	}
	public void destroyRandomBricks(int percentBricksToRemove) {
		int numberOfBricksToRemove = (int)(((numberOfRows * numberOfCols) / 100.0) * percentBricksToRemove);

		while(numberOfBricksToRemove > 0) {
			int randomRow = (int)(Math.random() * numberOfRows);
			int randomCol = (int)(Math.random() * numberOfCols);
			if(!bricks[randomRow][randomCol].isDestroyed() &&!bricks[randomRow][randomCol].isProtected()) {
				bricks[randomRow][randomCol].destroy();
				numberOfBricksToRemove--;
				bricksLeft--;
			}
		}
	}
	
	public void reset(){
		for(Brick[] col : bricks){
			for(Brick brick : col){
				brick.reset();
			}
		}
		bricksLeft = numberOfRows * numberOfCols;
		setLevel(currentLevel);
//		destroyRandomBricks(PERCENT_BRICKS_TO_REMOVE);  
		
	}

	public Brick[][] getBricks() {
		return bricks;
	}

	public void collision(Ball ball){
		//Old brick collision
		for(Brick[] brickCol : bricks){
			for(Brick brick : brickCol){
				if (!brick.isDestroyed()) {
					brick.collision(ball);
					if(brick.isDestroyed())
						bricksLeft--;
				}
			}
		}
		//Where ball meets brick
		//Use brick.collision(ball); on a limited amount of bricks.
		//Tip: Use ball.getBounds...(); as the hard limits of possibilities.
	}

	
	public Brick getBrick(int row, int col) {
		return bricks[row][col];
	}
	
	public boolean levelComplete() {
		return ((bricksLeft > 0) ? false: true);
	}
	
	public void tick(GameManager gManager, Ball ball) {
		collision(ball);
		if(levelComplete()) {
			gManager.victory();
		}
	}
}
