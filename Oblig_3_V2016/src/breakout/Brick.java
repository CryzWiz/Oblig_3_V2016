package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick implements Settings {
	private Rectangle rectangle;
	private boolean isDestroyed = false;

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
		isDestroyed = true;
	}
	
	public void reset(){
		rectangle.setDisable(false);
		rectangle.setVisible(true);
		isDestroyed = false;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	//A thorough collision model, angular collision, speed preserved
	public void collision(Ball ball){
		if(rectangle.isDisabled())
			return;
		switch(getPointZone(ball.getX(), ball.getY())){
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
			if(ball.dx < 0){
				if(ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsLeft(), getBoundsTop())){
						destroy();
						break;
					}
				} else {
					if(ball.bounceOffPoint(getBoundsLeft(), getBoundsBottom())){
						destroy();
						break;
					}
				}
			} else {
				if(ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsTop())){
						destroy();
						break;
					}
				} else {
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsBottom())){
						destroy();
						break;
					}
				}
			}
			if(Math.abs(ball.dx) > Math.abs(ball.dy))
				ball.bounceY();
			else
				ball.bounceX();
			destroy();
			break;
		}
	}

	//A simple collision model, no angular calculation
	public void collision2(Ball ball){
		if(!rectangle.isDisabled() && isWithinSpace(ball)){
			switch(getBallZone(ball)){
			case 1:
			case 7:
				ball.bounceY();
				destroy();
				break;
			case 3:
			case 5:
				ball.bounceX();
				destroy();
				break;
			case 4:
				if(isWithinBoundsV(calculateHorizontalIntersectY(ball)))
					ball.bounceX();
				else
					ball.bounceY();
				destroy();
			}
		}
	}

	//A advanced collision model, best of both worlds
	public void collision3(Ball ball){
		if(!rectangle.isDisabled() && isWithinSpace(ball)){
			switch(getBallZone(ball)){
			case 1: //Up & Down
			case 7:
				if(isWithinBoundsV(calculateHorizontalIntersectY(ball))){
					System.out.println((ball.dy > 0 ? "Top" : "Bot"));
					ball.bounceY();
					destroy();
				}
				break;
			case 3: //Left & Right
			case 5:
				if(isWithinBoundsH(calculateVerticalIntersectX(ball))){
					System.out.println((ball.dx > 0 ? "Left" : "Right"));
					ball.bounceX();
					destroy();
				}
				break;
			case 4: //Inside
				System.out.println("inside");
				if(ball.dx == 0)
					ball.bounceY();
				else if(ball.dy == 0)
					ball.bounceX();
				else if(!isWithinBoundsV(calculateHorizontalIntersectY(ball)))
					ball.bounceY();
				else if(!isWithinBoundsH(calculateVerticalIntersectX(ball)))
					ball.bounceX();
				else 
					System.out.println("Special case");
				destroy();
			}
		}
	}

	public double calculateHorizontalIntersectY(Ball ball){
		int relevantBound = (ball.dx > 0 ? getBoundsLeft() - ball.getRadius() : getBoundsRight() + ball.getRadius());
		return ball.getY() - (ball.getX()-relevantBound)*ball.dy/ball.dx;
	}
	public double calculateVerticalIntersectX(Ball ball){
		int relevantBound = (ball.dy > 0 ? getBoundsTop() - ball.getRadius() : getBoundsBottom() + ball.getRadius());
		return ball.getX() - (ball.getY()-relevantBound)*ball.dx/ball.dy;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}

	public boolean isWithinBoundsH(double x){
		if(x < getBoundsBottom() && x > getBoundsTop())
			return true;
		return false;
	}
	public boolean isWithinBoundsV(double y){
		if(y < getBoundsRight() && y > getBoundsLeft())
			return true;
		return false;
	}
	public boolean isWithinSpaceH(double x, double r){
		return x < getBoundsBottom() + r && x > getBoundsTop() - r;
	}
	public boolean isWithinSpaceV(double y, double r){
		return y < getBoundsRight() + r && y > getBoundsLeft() - r;
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
	public boolean isWithinSpace(Ball ball){
		return isWithinSpaceH(ball) && isWithinSpaceV(ball);
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

	public int getPointZone(double x, double y){
		int hPosition = 1;
		int vPosition = 1;
		if(x < getBoundsLeft())
			hPosition = 0;
		else if(x > getBoundsRight())
			hPosition = 2;
		if(y < getBoundsTop())
			vPosition = 0;
		else if(y > getBoundsBottom())
			vPosition = 2;
		return 3 * vPosition + hPosition;
	}
	
	public int getBallZone(Ball ball){
		return getPointZone(ball.getX(), ball.getY());
	}
	
	public int getBallPrevZone(Ball ball){
		return getPointZone(ball.getPrevX(), ball.getPrevY());
	}

	public void setFill(Paint value){
		rectangle.setFill(value);
	}
}
