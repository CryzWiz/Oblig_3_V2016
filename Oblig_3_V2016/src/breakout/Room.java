package breakout;

import javafx.scene.Scene;

public class Room implements Settings{
	private Scene room;

	public Room(Scene s){
		room = s;
	}

	public boolean collision(Ball ball){
		if(collideLeft(ball) || collideRight(ball))
			ball.bounceX();
		if(collideTop(ball))
			ball.bounceY();
		if(collideBot(ball)){
			if(BOUNCE_ON_BOT)
				ball.bounceY();
			else
				return true;
		}
		return false;
	}

	private boolean collideLeft(Ball ball){
		if(ball.getX() < ball.getRadius() && ball.dx < 0)
			return true;
		return false;
	}
	private boolean collideRight(Ball ball){
		if(ball.getX() > room.getWidth() - ball.getRadius() && ball.dx > 0)
			return true;
		return false;
	}
	private boolean collideTop(Ball ball){
		if(ball.getY() < ball.getRadius() && ball.dy < 0)
			return true;
		return false;
	}
	private boolean collideBot(Ball ball){
		if(ball.getY() > room.getHeight() - ball.getRadius() && ball.dy > 0)
			return true;
		return false;
	}
}
