package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	private Circle circle;
	int dx, dy;
	
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
	
	public void bounceX(){
		dx *= -1;
	}
	public void bounceY(){
		dy *= -1;
	}
	public void accelerate(int x, int y){
		dx = x;
		dy = y;
	}
	public void move(int x, int y){
		circle.setTranslateX(circle.getTranslateX() + x);
		circle.setTranslateY(circle.getTranslateY() + y);
	}
	
	public void tick(){
		circle.setTranslateX(circle.getTranslateX() + dx);
		circle.setTranslateY(circle.getTranslateY() + dy);
	}
}
