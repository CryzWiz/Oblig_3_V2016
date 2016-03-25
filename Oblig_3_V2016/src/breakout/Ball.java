package breakout;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball implements Settings {
	private Circle circle;
	double dx, dy;
	boolean gravityOn;

	public Ball(Pane pane){
		this(pane, BALL_START_DX, BALL_START_DY, BALL_RADIUS, BALL_COLOR);
	}
	public Ball(Pane pane, int dx, int dy, int r, Color c){
		this(pane, BALL_START_X, BALL_START_Y, BALL_START_DX, BALL_START_DY, BALL_RADIUS, BALL_COLOR);
	}
	public Ball(Pane pane, int x, int y, int dx, int dy, int r, Color c){
		this.dx = dx;
		this.dy = dy;
		circle = new Circle(0, 0, r, c);
		circle.setTranslateX(x);
		circle.setTranslateY(y);
		pane.getChildren().add(circle);
		gravityOn = BALL_GRAVITY_ON;
	}

	public void reset(){
		circle.setTranslateX(BALL_START_X);
		circle.setTranslateY(BALL_START_Y);
		dx = BALL_START_DX;
		dy = BALL_START_DY;
	}

	public int getX(){
		return (int)circle.getTranslateX();
	}
	public int getY(){
		return (int)circle.getTranslateY();
	}
	public int getRadius(){
		return (int)circle.getRadius();
	}
	public Circle getCircle(){
		return circle;
	}
	public int getBoundsLeft(){
		return (int)(circle.getTranslateX() - circle.getRadius());
	}
	public int getBoundsRight(){
		return (int)(circle.getTranslateX() + circle.getRadius());
	}
	public int getBoundsTop(){
		return (int)(circle.getTranslateY() - circle.getRadius());
	}
	public int getBoundsBottom(){
		return (int)(circle.getTranslateY() + circle.getRadius());
	}
	public double getDirectionAngle(){
		return Math.atan2(dy, dx);
	}
	public double getSpeed(){
		return Math.sqrt(dx*dx + dy*dy);
	}
	public double getDistanceSquared(int x, int y){
		return Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2);
	}
	public double getPrevX(){
		return circle.getTranslateX() - dx;
	}
	public double getPrevY(){
		return circle.getTranslateY() - dy;
	}

	public void setSpeedCoords(double x, double y){
		dx = x;
		dy = y;
	}
	public void setSpeedPolar(double speed, double rad){
		dx = speed * Math.cos(rad);
		dy = speed * Math.sin(rad);
	}

	public void bounceX(){
		dx *= -1;
	}
	public void bounceY(){
		dy *= -1;
	}
	public void bounce(double reflectionAngle){
		bounceX();
		bounceY();
		if(Math.abs(reflectionAngle - getDirectionAngle()) < Math.PI / 2)
			setSpeedPolar(getSpeed(), 2*reflectionAngle - getDirectionAngle());
		else
			setSpeedPolar(getSpeed(), 1.5*reflectionAngle-0.5*getDirectionAngle());
	}
	public boolean bounceOffPoint(int x, int y){
		if(getDistanceSquared(x, y) < Math.pow(circle.getRadius(), 2)){
			bounce(Math.atan2(y - getY(), x - getX()));
			return true;
		}
		return false;
	}
	public void accelerate(double x, double y){
		dx += x;
		dy += y;
	}
	public void friction(double x, double y, double xFactor, double yFactor){
		dx -= (dx - x) * xFactor;
		dy -= (dy - y) * yFactor;
	}
	public void move(double x, double y){
		circle.setTranslateX(circle.getTranslateX() + x);
		circle.setTranslateY(circle.getTranslateY() + y);
	}

	public void tick(){
		circle.setTranslateX(circle.getTranslateX() + dx);
		circle.setTranslateY(circle.getTranslateY() + dy);
		if(dy*dy < BALL_GRAVITY_THRESHOLD && gravityOn)
			dy += BALL_GRAVITY_FACTOR;
	}
}
