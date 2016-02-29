package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	private Circle ball;
	int dx, dy;
	
	Ball(){
		dx = BreakOut.BALL_START_DX;
		dy = BreakOut.BALL_START_DY;
		ball = new Circle(0, 0, BreakOut.BALL_RADIUS, BreakOut.BALL_COLOR);
		reset();
	}
	
	Ball(int dx, int dy, int r, Color c){
		this.dx = dx;
		this.dy = dy;
		ball = new Circle(0, 0, r, c);
		reset();
	}
	Ball(int x, int y, int dx, int dy, int r, Color c){
		this.dx = dx;
		this.dy = dy;
		ball = new Circle(0, 0, r, c);
		ball.setTranslateX(x);
		ball.setTranslateY(y);
	}
	
	public void reset(){
		ball.setTranslateX(BreakOut.BALL_START_X);
		ball.setTranslateY(BreakOut.BALL_START_Y);
	}
	
	public int getX(){
		return (int)ball.getTranslateX();
	}
	public int getY(){
		return (int)ball.getTranslateY();
	}
	public int getRadius(){
		return (int)ball.getRadius();
	}
	public Circle getCircle(){
		return ball;
	}
	
	public void bounceX(){
		dx *= -1;
	}
	public void bounceY(){
		dy *= -1;
	}
	
	public void tick(){
		ball.setTranslateX(ball.getTranslateX() + dx);
		ball.setTranslateY(ball.getTranslateY() + dy);
	}
}
