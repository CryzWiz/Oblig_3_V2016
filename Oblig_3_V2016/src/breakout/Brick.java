package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick implements Settings {
	private Rectangle rectangle;

	public Brick(int posX, int posY, Color c) {
		rectangle = new Rectangle(BRICK_WIDTH, BRICK_HEIGHT, c);
		rectangle.setTranslateX(posX);
		rectangle.setTranslateY(posY);
	}
	
	public Brick(int posX, int posY, int width, int height) {
		rectangle = new Rectangle(BRICK_WIDTH, BRICK_HEIGHT);
		rectangle.setTranslateX(posX);
		rectangle.setTranslateY(posY);
	}
	
	public void destroy(){
		rectangle.setDisable(true);
	}
	
	public void collision(Ball ball){
		switch(getBallDirection(ball.getX(), ball.getY())){
		case 1: //Collision from top
			if(ball.dy > 0 && ball.getBoundsBottom() > getBoundsTop() && isWithinSpaceH(ball))
				ball.bounceY();
			break;
		case 3: //Left
			if(ball.dx > 0 && ball.getBoundsRight() > getBoundsLeft()&& isWithinSpaceV(ball))
				ball.bounceX();
			break;
		case 5: //Right
			if(ball.dx < 0 && ball.getBoundsLeft() < getBoundsRight()&& isWithinSpaceV(ball))
				ball.bounceX();
			break;
		case 7:
			if(ball.dy < 0 && ball.getBoundsTop() < getBoundsBottom()&& isWithinSpaceH(ball))
				ball.bounceY();
			break;
		case 4:
			ball.bounceX();
			ball.bounceY();
			break;
		}
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}

	public boolean isWithinSpaceH(Ball ball){
		if(ball.getBoundsLeft() < getBoundsRight() && ball.getBoundsRight() > getBoundsLeft())
			return true;
		return false;
	}
	public boolean isWithinSpaceV(Ball ball){
		if(ball.getBoundsBottom() > getBoundsTop() && ball.getBoundsTop() < getBoundsBottom())
			return true;
		return false;
	}
	
	public int getBoundsLeft(){
		return (int)(rectangle.getTranslateX());
	}
	public int getBoundsRight(){
		return (int)(rectangle.getTranslateX() + rectangle.getWidth());
	}
	public int getBoundsTop(){
		return (int)(rectangle.getTranslateY());
	}
	public int getBoundsBottom(){
		return (int)(rectangle.getTranslateY() + rectangle.getHeight());
	}
	
	public int getBallDirection(int x, int y){
		int hPosition = 0;
		int vPosition = 0;
		if(x < rectangle.getTranslateX())
			hPosition = -1;
		else if(x > rectangle.getTranslateX() + rectangle.getWidth())
			hPosition = 1;
		if(y < rectangle.getTranslateY())
			vPosition = -1;
		else if(y > rectangle.getTranslateY() + rectangle.getHeight())
			vPosition = 1;
		return 3 * vPosition + hPosition;
	}
	
	public void setFill(Paint value){
		rectangle.setFill(value);
	}
}
