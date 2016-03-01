package breakout;

import javafx.scene.paint.Color;

public class BrickManager implements Settings {
	Brick[][] bricks;

	public BrickManager(int numberOfRows, int numberOfCols) { 
		createBricks(numberOfRows, numberOfCols);

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
	    		//rect.setFill(BRICK_COLOR);
	    		
	    		bricks[y][x]= brick;
	    		if(y < 3){
		    		bricks[y][x].setFill(Color.BLUE);
		    	}
		    	else if(y < 6){
		    		bricks[y][x].setFill(Color.GREEN);
		    	}
		    	else if(y < 9){
		    		bricks[y][x].setFill(Color.YELLOW);
		    	}
		    	else{
		    		bricks[y][x].setFill(Color.RED);
		    	}
	    	}
	    	
	    }
	    this.bricks = bricks;
	}
	

	public Brick[][] getBricks() {
		return bricks;
	}


}
