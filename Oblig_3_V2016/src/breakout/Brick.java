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
		rectangle.setVisible(false);
	}
	
	public void collision(Ball ball){
		if(rectangle.isDisabled())
			return;
		switch(getBallDirection(ball.getX(), ball.getY())){
		//Edge collisions:
		case 1: //Collision from top
			if(ball.dy > 0 && ball.getBoundsBottom() > getBoundsTop() && isWithinSpaceH(ball)){
				ball.bounceY();
				destroy();
			}
			break;
		case 3: //Left
			if(ball.dx > 0 && ball.getBoundsRight() > getBoundsLeft()&& isWithinSpaceV(ball)){
				ball.bounceX();
				destroy();
			}
			break;
		case 5: //Right
			if(ball.dx < 0 && ball.getBoundsLeft() < getBoundsRight()&& isWithinSpaceV(ball)){
				ball.bounceX();
				destroy();
			}
			break;
		case 7:
			if(ball.dy < 0 && ball.getBoundsTop() < getBoundsBottom()&& isWithinSpaceH(ball)){
				ball.bounceY();
				destroy();
			}
			break;
		//Corner Collisions:
		case 0: //Top-Left
			if(ball.dx > 0 || ball.dy > 0){
				if(ball.bounceOffPoint(getBoundsLeft(), getBoundsTop()))
					destroy();
			}
			break;
		case 2: //Top-Right
			if(ball.dx < 0 || ball.dy > 0){
				if(ball.bounceOffPoint(getBoundsRight(), getBoundsTop()))
					destroy();
			}
			break;
		case 6: //Bottom-Left
			if(ball.dx > 0 || ball.dy < 0){
				if(ball.bounceOffPoint(getBoundsLeft(), getBoundsBottom()))
					destroy();
			}
			break;
		case 8: //Bottom-Right
			if(ball.dx < 0 || ball.dy < 0){
				if(ball.bounceOffPoint(getBoundsRight(), getBoundsBottom()))
					destroy();
			}
			break;
		case 4:
			ball.bounceX();
			ball.bounceY();
			destroy();
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
		int hPosition = 1;
		int vPosition = 1;
		if(x < rectangle.getTranslateX())
			hPosition = 0;
		else if(x > rectangle.getTranslateX() + rectangle.getWidth())
			hPosition = 2;
		if(y < rectangle.getTranslateY())
			vPosition = 0;
		else if(y > rectangle.getTranslateY() + rectangle.getHeight())
			vPosition = 2;
		return 3 * vPosition + hPosition;
	}
	
	public void setFill(Paint value){
		rectangle.setFill(value);
	}
}
