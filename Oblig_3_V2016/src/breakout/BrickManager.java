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
	    for(int y = 0; y < numberOfRows; y++){
	    	for(int x = 0; x < numberOfCols; x++){
	    		Brick brick = new Brick(
	    				x * (BRICK_WIDTH + BRICK_PADDING_H) + WALL_PADDING_LEFT,
	    				y * (BRICK_HEIGHT + BRICK_PADDING_V) + WALL_PADDING_TOP,
	    				BRICK_WIDTH,
	    				BRICK_HEIGHT);
	    		
	    		bricks[y][x]= brick;
	    	}
	   
	    }
	    this.bricks = bricks;
	}
	public void setBrickColors(Color colorTop, Color colorMid1, Color colorMid2, Color colorBottom) {
	    for(int y = 0; y < bricks.length; y++){
	    	for(int x = 0; x < bricks[y].length; x++){
	    		if(y < 3){
		    		bricks[y][x].setFill(colorTop);
		    	}
		    	else if(y < 6){
		    		bricks[y][x].setFill(colorMid1);
		    	}
		    	else if(y < 9){
		    		bricks[y][x].setFill(colorMid2);
		    	}
		    	else{
		    		bricks[y][x].setFill(colorBottom);
		    	}
	    		
	    	}
	    }
	}
	

	public Brick[][] getBricks() {
		return bricks;
	}


}
