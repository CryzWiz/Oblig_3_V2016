package breakout;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	private Brick[][] bricks;
	private int numberOfRows;
	private int numberOfCols;
	private int bricksLeft;

	public BrickManager(Pane pane) { 
		this(pane, BRICK_ROWS, BRICK_COLS);
	}
	public BrickManager(Pane pane, int numberOfRows, int numberOfCols) { 
		this(pane, numberOfRows, numberOfCols, BRICK_COLOR_LAYER1, BRICK_COLOR_LAYER2, BRICK_COLOR_LAYER3, BRICK_COLOR_LAYER4);
	}
	public BrickManager(Pane pane, int numberOfRows, int numberOfCols, Color colorTop, Color colorMid1, Color colorMid2, Color colorBottom) { 
		createBricks(numberOfRows, numberOfCols);
		bricksLeft = numberOfRows * numberOfCols;
		setBrickColors(colorTop, colorMid1, colorMid2, colorBottom);
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		destroyRandomBricks(20);
		for(int row = 0; row < BRICK_ROWS; row++){
			for(int col = 0; col < BRICK_COLS; col++){
				pane.getChildren().add(bricks[row][col].getRectangle());
			}
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
	public void setBrickColors(Color colorTop, Color colorMid1, Color colorMid2, Color colorBottom) {
		for(int row = 0; row < bricks.length; row++){
			for(int col = 0; col < bricks[row].length; col++){
				if(row < 3){
					bricks[row][col].setFill(colorTop);
				}
				else if(row < 6){
					bricks[row][col].setFill(colorMid1);
				}
				else if(row < 9){
					bricks[row][col].setFill(colorMid2);
				}
				else{
					bricks[row][col].setFill(colorBottom);
				}

			}
		}
	}
	public void destroyRandomBricks(int percentBricksToRemove) {
		int numberOfBricksToRemove = (int)(((numberOfRows * numberOfCols) / 100.0) * percentBricksToRemove);

		while(numberOfBricksToRemove > 0) {
			int randomRow = (int)(Math.random() * numberOfRows);
			int randomCol = (int)(Math.random() * numberOfCols);
			if(!bricks[randomRow][randomCol].isDestroyed()) {
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
		destroyRandomBricks(98);  // ------------------------------- MUST BE CHANGED, only set for testint purpose
		
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
		System.out.println(bricksLeft); // --------------------------- REMOVE! Only for testing
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
