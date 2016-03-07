package breakout;

import javafx.scene.Scene;

public class Room implements Settings{
	Scene room;

	public Room(Scene s){
		room = s;
		room.setFill(BACKGROUND_COLOR);
	}

	public boolean collision(Ball ball){
		if(collideLeft(ball) || collideRight(ball))
			ball.bounceX();
		if(collideTop(ball))
			ball.bounceY();
		if(collideRacket(ball))
			ball.bounceY();
		else if(collideBot(ball)){
			if(BOUNCE_ON_BOT)
				ball.bounceY();
			else
				return true;
		}
		return false;
	}
	public boolean collideRacket(Ball ball){
		if(ball.getY() > PADDLE_Y_OFFSET)
			if(ball.getX() < (Racket.dx + (PADDLE_WIDTH / 2)) && 
					ball.getX() > (Racket.dx - (PADDLE_WIDTH / 2)))
				return true;
		return false;
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
