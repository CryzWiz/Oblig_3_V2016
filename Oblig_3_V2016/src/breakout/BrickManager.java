package breakout;

import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	Brick[][] bricks;
	
	public BrickManager() { 
		createBricks(BRICK_ROWS, BRICK_COLS);
		setBrickColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);
	}

	public BrickManager(int numberOfRows, int numberOfCols) { 
		createBricks(numberOfRows, numberOfCols);
		setBrickColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED);

	}
	public BrickManager(int numberOfRows, int numberOfCols, Color colorTop, Color colorMid1, Color colorMid2, Color colorBottom) { 
		createBricks(numberOfRows, numberOfCols);
		setBrickColors(colorTop, colorMid1, colorMid2, colorBottom);

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
	

	public Brick[][] getBricks() {
		return bricks;
	}


}
