package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	private Circle circle;
	double dx, dy; //Convert int to double????
	
	Ball(){
		dx = BreakOut.BALL_START_DX;
		dy = BreakOut.BALL_START_DY;
		circle = new Circle(0, 0, BreakOut.BALL_RADIUS, BreakOut.BALL_COLOR);
		reset();
	}
	
	Ball(int dx, int dy, int r, Color c){
		this.dx = dx;
		this.dy = dy;
		circle = new Circle(0, 0, r, c);
		reset();
	}
	Ball(int x, int y, int dx, int dy, int r, Color c){
		this.dx = dx;
		this.dy = dy;
		circle = new Circle(0, 0, r, c);
		circle.setTranslateX(x);
		circle.setTranslateY(y);
	}
	
	public void reset(){
		circle.setTranslateX(BreakOut.BALL_START_X);
		circle.setTranslateY(BreakOut.BALL_START_Y);
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
		setSpeedPolar(getSpeed(), 2*reflectionAngle - getDirectionAngle());
	}
	public boolean bounceOffPoint(int x, int y){
		if(getDistanceSquared(x, y) < Math.pow(circle.getRadius(), 2)){
			bounce(Math.atan2(y - getY(), x - getX()));
			return true;
		}
		return false;
	}
	public void accelerate(double x, double y){
		dx = x;
		dy = y;
	}
	public void move(double x, double y){
		circle.setTranslateX(circle.getTranslateX() + x);
		circle.setTranslateY(circle.getTranslateY() + y);
	}
	
	public void tick(){
		circle.setTranslateX(circle.getTranslateX() + dx);
		circle.setTranslateY(circle.getTranslateY() + dy);
	}
}
