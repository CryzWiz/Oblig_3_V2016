package breakout;

import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	private Brick[][] bricks;
	private int numberOfRows;
	private int numberOfCols;
	
	public BrickManager() { 
		this(BRICK_ROWS, BRICK_COLS, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
	}

	public BrickManager(int numberOfRows, int numberOfCols) { 
		this(numberOfRows, numberOfCols, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
	}
	
	public BrickManager(int numberOfRows, int numberOfCols, Color colorTop, Color colorMid1, Color colorMid2, Color colorBottom) { 
		createBricks(numberOfRows, numberOfCols);
		setBrickColors(colorTop, colorMid1, colorMid2, colorBottom);
		this.numberOfRows = numberOfRows;
		this.numberOfCols = numberOfCols;
		destroyRandomBricks();
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
	public void destroyRandomBricks() {
	int numberOfBricksToRemove = (int)(((numberOfRows * numberOfCols) / 100.0) * 20);
	
	for(int i = 0; i <= numberOfBricksToRemove; i++) {
		int randomRow = (int)(Math.random() * numberOfRows);
		int randomCol = (int)(Math.random() * numberOfCols);
		bricks[randomRow][randomCol].destroy();
	}
}

	public Brick[][] getBricks() {
		return bricks;
	}


}
