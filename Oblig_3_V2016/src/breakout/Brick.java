package breakout;

import static breakout.Brick.CollisionType.*;
import static breakout.Brick.Zone.*;
import javafx.animation.FadeTransition;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Brick implements Settings {
	public enum CollisionModel{OLD, SIMPLE, FULL, MIXED};
	public enum CollisionType{EDGE, EDGE_DOUBLE, CORNER_ANGLE, CORNER_SIMPLE, INSIDE_CORNER, INSIDE, DONE, NOTHING, NO_RANGE};
	public enum Zone{TOP_LEFT, TOP, TOP_RIGHT, LEFT, MIDDLE, RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT};
	public enum BrickType{NORMAL, DURABLE, PROTECTED, UNBREAKABLE};

	private Brick[] closeBricks = new Brick[2];
	private Rectangle rectangle;
	private FadeTransition fader;
	private boolean destroyed = false, protection = false, unbreakable = false;
	private int durability = SINGLE_HIT;

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
		return destroyed;
	}
	
	private boolean hasBrickOnRight(){
		return !(closeBricks[0] == null || closeBricks[0].isDestroyed());
	}
	private boolean hasBrickOnBottom(){
		return !(closeBricks[1] == null || closeBricks[1].isDestroyed());
	}

	public Zone getPointZone(double x, double y){
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
		return Zone.values()[3 * vPosition + hPosition];
	}
	public Zone getBallZone(Ball ball){

		return getPointZone(ball.getX(), ball.getY());
	}
	public Zone getBallPrevZone(Ball ball){
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
		if(durability == SINGLE_HIT) {
			if(!unbreakable) {
				fader = new FadeTransition(Duration.millis(1000), rectangle);
				fader.setFromValue(1.0);
				fader.setToValue(0.0);
				fader.play();
				//rectangle.setDisable(true);
				//rectangle.setVisible(false);
				destroyed = true;
			}
		} else {
			decreaseDurability();
		}
	}

	public void reset(){
		setUnbreakable(false);
		setDurability(SINGLE_HIT);
		setProtection(false);
		destroyed = false;
		fader = new FadeTransition(Duration.millis(100), rectangle);
		fader.setFromValue(0.0);
		fader.setToValue(1.0);
		fader.play();
		rectangle.setDisable(false);
	}

	/**
	 * Protection from random destruction
	 */
	public void setProtection(boolean protection) {
		this.protection = protection;
	}
	public boolean isProtected() {
		return protection;
	}
	
	public void setUnbreakable(boolean unbreakable) {
		this.unbreakable = unbreakable;
	}
	public boolean isUnbreakable() {
		return unbreakable;
	}
	
	public void setDurability(int durability) {
		switch(durability) {
		case SINGLE_HIT:
			this.durability = SINGLE_HIT;
			break;
		case DOUBLE_HIT:
			this.durability = DOUBLE_HIT;
			break;
		case TRIPLE_HIT:
			this.durability = TRIPLE_HIT;
			break;
		default:
			this.durability = SINGLE_HIT;
		}
	}
	private void decreaseDurability() {
		switch(durability) {
		case TRIPLE_HIT:
			durability = DOUBLE_HIT;
			break;
		case DOUBLE_HIT:
			durability = SINGLE_HIT;
			break;
		default:
			durability = SINGLE_HIT;
		}
		
	}
	
	private void collision_old(Ball ball){
		if(isInMaxRange(ball))
			return;
		switch(getPointZone(ball.getX(), ball.getY())){
		//Edge collisions:
		case TOP:
			if(ball.dy > 0 && ball.getBoundsBottom() > getBoundsTop() && isInMaxRangeX(ball)){
				ball.bounceY();
				destroy();
			}
			break;
		case LEFT:
			if(ball.dx > 0 && ball.getBoundsRight() > getBoundsLeft()&& isInMaxRangeY(ball)){
				ball.bounceX();
				destroy();
			}
			break;
		case RIGHT:
			if(ball.dx < 0 && ball.getBoundsLeft() < getBoundsRight()&& isInMaxRangeY(ball)){
				ball.bounceX();
				destroy();
			}
			break;
		case BOTTOM:
			if(ball.dy < 0 && ball.getBoundsTop() < getBoundsBottom()&& isInMaxRangeX(ball)){
				ball.bounceY();
				destroy();
			}
			break;
			//Corner Collisions:
		case TOP_LEFT:
			if(ball.dx > 0 || ball.dy > 0){
				if(ball.bounceOffPoint(getBoundsLeft(), getBoundsTop()))
					destroy();
			}
			break;
		case TOP_RIGHT:
			if(ball.dx < 0 || ball.dy > 0){
				if(ball.bounceOffPoint(getBoundsRight(), getBoundsTop()))
					destroy();
			}
			break;
		case BOTTOM_LEFT:
			if(ball.dx > 0 || ball.dy < 0){
				if(ball.bounceOffPoint(getBoundsLeft(), getBoundsBottom()))
					destroy();
			}
			break;
		case BOTTOM_RIGHT:
			if(ball.dx < 0 || ball.dy < 0){
				if(ball.bounceOffPoint(getBoundsRight(), getBoundsBottom()))
					destroy();
			}
			break;
		case MIDDLE:
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
	@SuppressWarnings("incomplete-switch")
	private void collision_simple(Ball ball){
		if(isInMaxRange(ball)){
			switch(getBallZone(ball)){
			case TOP:
			case BOTTOM:
				ball.bounceY();
				destroy();
				break;
			case LEFT:
			case RIGHT:
				ball.bounceX();
				destroy();
				break;
			case MIDDLE:
				if(isInCloseRangeY(yWhenEnterCloseRange(ball)))
					ball.bounceX();
				else
					ball.bounceY();
				destroy();
			}
		}
	}
	private void collision_full(Ball ball){
		if(isInMaxRange(ball)){
			switch(getBallZone(ball)){
			case TOP:
			case BOTTOM:
				if(ball.dy != 0){
					if(!isInMaxRangeX(xWhenEnterCloseRange(ball), ball.getRadius()))
						break;
				}
				ball.bounceY();
				destroy();
				break;
			case LEFT:
			case RIGHT:
				if(ball.dx != 0){
					if(!isInMaxRangeY(yWhenEnterCloseRange(ball), ball.getRadius()))
						break;
				}
				ball.bounceX();
				destroy();
				break;
			//Corners
			case TOP_LEFT:
				if(ball.dx > 0 || ball.dy > 0){
					if(ball.bounceOffPoint(getBoundsLeft(), getBoundsTop()))  
						destroy();
				}
				break;
			case TOP_RIGHT:
				if(ball.dx < 0 || ball.dy > 0){
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsTop()))
						destroy();
				}
				break;
			case BOTTOM_LEFT:
				if(ball.dx > 0 || ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsLeft(), getBoundsBottom()))
						destroy();
				}
				break;
			case BOTTOM_RIGHT:
				if(ball.dx < 0 || ball.dy < 0){
					if(ball.bounceOffPoint(getBoundsRight(), getBoundsBottom()))
						destroy();
				}
				break;
			case MIDDLE:
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
	}
	private CollisionType collision_mixed(Ball ball){
		if(!isInMaxRange(ball)){
			return NO_RANGE;
		}
		switch(getBallZone(ball)){

		//Edges type collision
		case TOP:
			return collisionSwitch(ball, TOP);
		case BOTTOM:
			return collisionSwitch(ball, BOTTOM);
		case LEFT:
			return collisionSwitch(ball, LEFT);
		case RIGHT:
			return collisionSwitch(ball, RIGHT);

		//Corner type collision
		case TOP_LEFT:
			return collisionSwitch(ball, TOP_LEFT);
		case TOP_RIGHT:
			if(!hasBrickOnRight())
				return collisionSwitch(ball, TOP_RIGHT);
			collisionSwitch(ball, TOP);
			return EDGE_DOUBLE;
		case BOTTOM_LEFT:
			if(!hasBrickOnBottom())
				return collisionSwitch(ball, BOTTOM_LEFT);
			collisionSwitch(ball, LEFT);
			return EDGE_DOUBLE;
		case BOTTOM_RIGHT:
			if(!hasBrickOnBottom() && !hasBrickOnRight())
				return collisionSwitch(ball, BOTTOM_RIGHT);
			else if(hasBrickOnRight()){
				collisionSwitch(ball, BOTTOM);
				return EDGE_DOUBLE;
			}
			else if(hasBrickOnBottom()){
				collisionSwitch(ball, RIGHT);
				return EDGE_DOUBLE;
			}
			return NOTHING;
		case MIDDLE:
		default:
			if(Math.abs(ball.dx) > Math.abs(ball.dy))
				ball.bounceX();
			else
				ball.bounceY();
			destroy();
			return INSIDE;
		}
	}

	private CollisionType collisionSwitch(Ball ball, Zone collisionCase){
		switch(collisionCase){
		case TOP:
			if(ball.dy < 0)
				return DONE;
			ball.bounceY();
			destroy();
			return EDGE;
		case BOTTOM:
			if(ball.dy > 0)
				return DONE;
			ball.bounceY();
			destroy();
			return EDGE;
		case LEFT:
			if(ball.dx < 0)
				return DONE;
			ball.bounceX();
			destroy();
			return EDGE;
		case RIGHT:
			if(ball.dx > 0)
				return DONE;
			ball.bounceX();
			destroy();
			return EDGE;
		case TOP_LEFT:
			if(ball.dx > 0 && ball.dy > 0){
				if(ball.bounceOffPoint(getBoundsLeft(), getBoundsTop())){  
					destroy();
					return CORNER_ANGLE;
				}
				return NO_RANGE;
			}
			return DONE;
		case TOP_RIGHT:
			if(ball.dx < 0 && ball.dy > 0){
				if(ball.bounceOffPoint(getBoundsRight(), getBoundsTop())){  
					destroy();
					return CORNER_ANGLE;
				}
				return NO_RANGE;
			}
			return DONE;
		case BOTTOM_LEFT:
			if(ball.dx > 0 && ball.dy < 0){
				if(ball.bounceOffPoint(getBoundsLeft(), getBoundsBottom())){  
					destroy();
					return CORNER_ANGLE;
				}
				return NO_RANGE;
			}
			return DONE;
		case BOTTOM_RIGHT:
			if(ball.dx < 0 && ball.dy < 0){
				if(ball.bounceOffPoint(getBoundsRight(), getBoundsBottom())){  
					destroy();
					return CORNER_ANGLE;
				}
				return NO_RANGE;
			}
			return DONE;
		default:
			return NOTHING;
		}
	}
	
	public void collision(Ball ball, CollisionModel model){
		switch(model){
		case OLD:
			collision_old(ball);
			break;
		case SIMPLE:
			collision_simple(ball);
			break;
		case MIXED:
			collision_mixed(ball);
			break;
		case FULL:
			collision_full(ball);
			break;
		}
	}
	public CollisionType collision(Ball ball){
		CollisionType type = collision_mixed(ball);
		/*if(type != DONE && type != NO_RANGE){ //Debugging purposes
			System.out.println(type);
		}*/
		return type;
	}
}
