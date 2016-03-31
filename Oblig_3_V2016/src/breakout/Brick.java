package breakout;

import static breakout.Brick.COLLISION_TYPE.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick implements Settings {
	public enum COLLISION_TYPE{EDGE, CORNER_ANGLE, CORNER_SIMPLE, INSIDE_CORNER, INSIDE, NOTHING};
	private Brick[] closeBricks = new Brick[2];
	private Rectangle rectangle;
	private boolean isDestroyed = false;
	private boolean isProtected = false;
	private boolean unbreakable = false;

	public Brick(int posX, int posY) {
		this(posX, posY, BRICK_WIDTH, BRICK_HEIGHT);
	}
	public Brick(int posX, int posY, int width, int height) {
		rectangle = new Rectangle(width, height);
		rectangle.setTranslateX(posX);
		rectangle.setTranslateY(posY);
		closeBricks[0] = null;
		closeBricks[1] = null;
	}

	public void setCloseBrick(int index, Brick brick){
		closeBricks[index] = brick;
	}
	public void setFill(Paint value){
		rectangle.setFill(value);
	}
	public Rectangle getRectangle(){
		return rectangle;
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

	public boolean isInCloseRangeX(double x){
		if(x < getBoundsRight() && x > getBoundsLeft())
			return true;
		return false;
	}
	public boolean isInCloseRangeY(double y){
		if(y < getBoundsBottom() && y > getBoundsTop())
			return true;
		return false;
	}
	public boolean isInMaxRangeX(double x, double r){
		return x < getBoundsRight() + r && x > getBoundsLeft() - r;
	}
	public boolean isInMaxRangeY(double y, double r){
		return y < getBoundsBottom() + r && y > getBoundsTop() - r;
	}
	public boolean isInMaxRangeX(Ball ball){
		if(ball.getBoundsLeft() < getBoundsRight() && ball.getBoundsRight() > getBoundsLeft())
			return true;
		return false;
	}
	public boolean isInMaxRangeY(Ball ball){
		if(ball.getBoundsBottom() > getBoundsTop() && ball.getBoundsTop() < getBoundsBottom())
			return true;
		return false;
	}
	public boolean isInMaxRange(Ball ball){
		return isInMaxRangeX(ball) && isInMaxRangeY(ball);
	}
	public boolean isDestroyed() {
		return isDestroyed;
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

	public double yWhenEnterCloseRange(Ball ball){
		int relevantBound = (ball.dx > 0 ? getBoundsLeft() - ball.getRadius() : getBoundsRight() + ball.getRadius());
		//return ball.getY() - (Math.abs(ball.getX()-relevantBound))*ball.dy/ball.dx;
		return ball.getY() - (ball.getX()-relevantBound)*ball.dy/ball.dx;
	}
	public double xWhenEnterCloseRange(Ball ball){
		int relevantBound = (ball.dy > 0 ? getBoundsTop() - ball.getRadius() : getBoundsBottom() + ball.getRadius());
		return ball.getX() - (ball.getY()-relevantBound)*ball.dx/ball.dy;
	}

	public void destroy(){
		if(!unbreakable) {
			rectangle.setDisable(true);
			rectangle.setVisible(false);
			isDestroyed = true;
		}
	}
	public void reset(){
		rectangle.setDisable(false);
		rectangle.setVisible(true);
		isDestroyed = false;
	}

	/**
	 * Protection from random destruction
	 */
	public void setProtection(boolean protection) {
		isProtected = protection;
	}
	public boolean isProtected() {
		return isProtected;
	}
	
	public void setUnbreakable(boolean unbreakable) {
		this.unbreakable = unbreakable;
	}
	public boolean isUnbreakable() {
		return unbreakable;
	}
	
	private void collision_old(Ball ball){
		if(isInMaxRange(ball))
			return;
		switch(getPointZone(ball.getX(), ball.getY())){
		//Edge collisions:
		case 1: //Collision from top
			if(ball.dy > 0 && ball.getBoundsBottom() > getBoundsTop() && isInMaxRangeX(ball)){
				ball.bounceY();
				destroy();
			}
			break;
		case 3: //Left
			if(ball.dx > 0 && ball.getBoundsRight() > getBoundsLeft()&& isInMaxRangeY(ball)){
				ball.bounceX();
				destroy();
			}
			break;
		case 5: //Right
			if(ball.dx < 0 && ball.getBoundsLeft() < getBoundsRight()&& isInMaxRangeY(ball)){
				ball.bounceX();
				destroy();
			}
			break;
		case 7:
			if(ball.dy < 0 && ball.getBoundsTop() < getBoundsBottom()&& isInMaxRangeX(ball)){
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
	private void collision_simple(Ball ball){
		if(isInMaxRange(ball)){
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
				if(isInCloseRangeY(yWhenEnterCloseRange(ball)))
					ball.bounceX();
				else
					ball.bounceY();
				destroy();
			}
		}
	}
	private COLLISION_TYPE collision_full(Ball ball){
		if(!isInMaxRange(ball)){
			return NOTHING;
		}
		/*System.out.println("\n\n" + getBoundsLeft() + ", " + getBoundsRight());
		System.out.println(getBoundsTop() + ", " + getBoundsBottom());
		System.out.println(ball.dx + ", " + ball.dy);
		System.out.print(ball.getX() + ", " + ball.getY());*/ //For Debugging purposes
		switch(getBallZone(ball)){
		case 1: //Top
		case 7: //Bot
			if(ball.dy != 0){
				if(!isInMaxRangeX(xWhenEnterCloseRange(ball), ball.getRadius()))
					return NOTHING;
			}
			//System.out.println((ball.dy > 0 ? " - TOP" : " - BOT")); //For debugging purposes
			ball.bounceY();
			destroy();
			return EDGE;
		case 3: //Left
		case 5: //Right
			if(ball.dx != 0){
				if(!isInMaxRangeY(yWhenEnterCloseRange(ball), ball.getRadius()))
					return NOTHING;
			}
			//System.out.println((ball.dx > 0 ? " - LEFT" : " - RIGHT")); //For debugging purposes
			ball.bounceX();
			destroy();
			return EDGE;
		//Corners
		case 0: //Top-Left
			if(ball.dx > 0 || ball.dy > 0){ // TASK: Move distance check from bounceOffPoint into if-test
				if(ball.bounceOffPoint(getBoundsLeft(), getBoundsTop())){  
					destroy();
					return CORNER_ANGLE;
				}
			}
			return NOTHING;
		case 2: //Top-Right
			if(closeBricks[0] == null || closeBricks[0].isDestroyed()){
				if(ball.dx < 0 || ball.dy > 0){
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsTop())){
						destroy();
						return CORNER_ANGLE;
					}
				}
				return NOTHING;
			}
			else{
				collision_simple(ball);
				return CORNER_SIMPLE;
			}
		case 6: //Bottom-Left
			if(closeBricks[1] == null || closeBricks[1].isDestroyed()){
				if(ball.dx > 0 || ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsLeft(), getBoundsBottom())){
						destroy();
						return CORNER_ANGLE;
					}
				}
				return NOTHING;
			}
			else{
				collision_simple(ball);
				return CORNER_SIMPLE;
			}
		case 8: //Bottom-Right
			if((closeBricks[0] == null || closeBricks[0].isDestroyed()) &&
					(closeBricks[1] == null || closeBricks[1].isDestroyed())){
				if(ball.dx < 0 || ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsBottom())){
						destroy();
						return CORNER_ANGLE;
					}
				}
				return NOTHING;
			}
			else{
				collision_simple(ball);
				return CORNER_SIMPLE;
			}
		case 4: //Inside
		default:
			ball.bounceX();
			ball.bounceY();
			if(ball.dx < 0){
				if(ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsLeft(), getBoundsTop())){
						destroy();
						return INSIDE_CORNER;
					}
				} else {
					if(ball.bounceOffPoint(getBoundsLeft(), getBoundsBottom())){
						destroy();
						return INSIDE_CORNER;
					}
				}
			} else {
				if(ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsTop())){
						destroy();
						return INSIDE_CORNER;
					}
				} else {
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsBottom())){
						destroy();
						return INSIDE_CORNER;
					}
				}
			}
			if(Math.abs(ball.dx) > Math.abs(ball.dy))
				ball.bounceY();
			else
				ball.bounceX();
			destroy();
			return INSIDE;
		}
	}

	public void collision(Ball ball, int version){
		switch(version){
		case COLLISION_MODEL_OLD:
			collision_old(ball);
			break;
		case COLLISION_MODEL_SIMPLE:
			collision_simple(ball);
			break;
		case COLLISION_MODEL_FULL:
			collision_full(ball);
			break;
		}
	}
	public COLLISION_TYPE collision(Ball ball){
		COLLISION_TYPE type = collision_full(ball);
		if(type != NOTHING){
			System.out.println(type);
		}
		return type;
	}
}
