package breakout;

import static breakout.BrickManager.Level.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	public static enum Level{
		ONE(1, 99), TWO(2, 15), THREE(3, 10), VICTORY(4, 0);
		
		int value;
		int removePercentage;
		public static int level;
		
		Level(int value, int removePercentage){
			this.value = value;
			this.removePercentage = removePercentage;
		}
		
		public int value(){
			level = value;
			return value;
		}
		public static int getvalue(){
			return level;
		}
		public int percentage(){
			return removePercentage;
		}
		
		public Level nextLevel(){
			switch(this){
			case ONE:
				return TWO;
			case TWO:
				return THREE;
			case THREE:
				return VICTORY;
			default:
				return ONE;
			}
		}
	};
	private Brick[][] bricks;
	private int numberOfRows;
	private int numberOfCols;
	private int bricksLeft;
	//private int currentLevel;
	private Level currentLevel;
	

	public BrickManager(Pane pane) { 
		this(pane, BRICK_ROWS, BRICK_COLS);
	}

	public BrickManager(Pane pane, int numberOfRows, int numberOfCols) { 		
		createBricks(numberOfRows, numberOfCols);
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		bricksLeft = numberOfRows * numberOfCols;
		setLevel(ONE); //-------------------------------------------------
		for(int row = 0; row < BRICK_ROWS; row++){
			for(int col = 0; col < BRICK_COLS; col++){
				pane.getChildren().add(bricks[row][col].getRectangle());
			}
		}
	}
	
	//public void setLevel(int lvl) {//-------------------------------------------------
	public void setLevel(Level lvl) {//-------------------------------------------------
		currentLevel = lvl;
		reset();
		switch(currentLevel) {
			case ONE:
				ScreenManager.lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
				ScreenManager.lvl2Box.setOpacity(OPACITY_LEVELICONS_ON);
				ScreenManager.lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
				setBrickColors(BRICK_COLORS_LVL1);
				break;
			case TWO:
				ScreenManager.lvl1Box.setOpacity(OPACITY_LEVELICONS_OFF);
				ScreenManager.lvl2Box.setOpacity(OPACITY_LEVELICONS_OFF);
				ScreenManager.lvl3Box.setOpacity(OPACITY_LEVELICONS_ON);
				setBrickColors(BRICK_COLORS_LVL2);
				break;
			case THREE:
				
				break;
			case VICTORY:
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
		case ONE:
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
		case TWO:
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
		case THREE:
			break;
		case VICTORY:
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
		//setLevel(currentLevel);
		destroyRandomBricks(currentLevel.percentage());
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
			setLevel(currentLevel.nextLevel());
		}
	}
}
