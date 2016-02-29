package breakout;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Room {
	Scene room;

	public Room(Scene s, Color c){
		room = s;
	    room.setFill(c);
	}
	
	public void collision(Ball ball){
		if(collideLeft(ball) || collideRight(ball))
			ball.bounceX();
		if(collideTop(ball) || collideBot(ball))
			ball.bounceY();
	}
	
	public boolean collideLeft(Ball ball){
		if(ball.getX() < ball.getRadius() && ball.dx < 0)
			return true;
		return false;
	}
	public boolean collideRight(Ball ball){
		if(ball.getX() > room.getWidth() - ball.getRadius() && ball.dx > 0)
			return true;
		return false;
	}
	public boolean collideTop(Ball ball){
		if(ball.getY() < ball.getRadius() && ball.dy < 0)
			return true;
		return false;
	}
	public boolean collideBot(Ball ball){
		if(ball.getY() > room.getHeight() - ball.getRadius() && ball.dy > 0)
			return true;
		return false;
	}
}
