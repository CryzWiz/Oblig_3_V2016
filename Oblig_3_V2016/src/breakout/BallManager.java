package breakout;

import static breakout.BallManager.Speed.*;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.layout.Pane;

public class BallManager implements Settings {
	public enum Speed{
		SLOW("Slow", 0.7), NORMAL("Normal", 1), FAST("Fast", 1.3);
		
		private String text;
		private double factor;
		public static Speed speed = NORMAL;
		
		Speed(String text, double factor){
			this.text = text;
			this.factor = factor;
		}
		
		public static void cycle(){
			switch(speed){
			case SLOW:
				speed = NORMAL;
				break;
			case NORMAL:
				speed = FAST;
				break;
			case FAST:
				speed = SLOW;
				break;
			}
		}
		
		@Override
		public String toString(){
			return text;
		}
		public double getValue(){
			return factor;
		}
	};
	private ArrayList<Ball> balls = new ArrayList<>();
	private GameManager gameManager;
	private BrickManager brickManager;
	private Racket racket;
	private Room room;
	private Pane pane;

	public BallManager(GameManager gameManager, BrickManager brickManager, Racket racket, Room room, Pane pane) {
		this.gameManager = gameManager;
		this.brickManager = brickManager;
		this.racket = racket;
		this.room = room;
		this.pane = pane;
	}
	
	public void add(Ball ball){
		if(!balls.contains(ball))
			balls.add(ball);
	}
	public void add(int x, int y, int dx, int dy){
		balls.add(new Ball(pane, x, y, dx, dy));
	}
	
	public Ball get(int i){
		return balls.get(i);
	}
	
	public void reset(){
		Iterator<Ball> bIterator = balls.iterator();
		while(bIterator.hasNext()){
			Ball ball = bIterator.next();
			pane.getChildren().remove(ball.getCircle());
			bIterator.remove();
		}
		balls.add(new Ball(pane));
		balls.get(0).setSpeedCoords(BALL_START_DX * speed.getValue(), BALL_START_DY * speed.getValue());
	}
	public void toggleSpeed(){
		cycle();
		MenuButton.setText(2, "Ball Speed: " + speed.toString());
	}
	public boolean collision(Ball ball){ //true if ball falls into void
		brickManager.tick(gameManager, ball);
		racket.collision(ball);
		if(room.collision(ball)){
			return true;
		}
		return false;
	}
	public void tick(){
		Iterator<Ball> bIterator = balls.iterator();
		while(bIterator.hasNext()) {
			Ball ball = bIterator.next();
			ball.tick();
			if(collision(ball)){
				pane.getChildren().remove(ball.getCircle());
				bIterator.remove();
				if(balls.isEmpty()){
					gameManager.gameOver();
					break;
				}
			}
		}
	}
}
