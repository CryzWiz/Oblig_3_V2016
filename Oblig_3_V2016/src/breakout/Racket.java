package breakout;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static breakout.Racket.Direction.*;

public class Racket implements Settings{
	public static enum Direction{
		LEFT(-1, Color.RED), STILL(0, PADDLE_COLOR), RIGHT(1, Color.BLUE);
		
		public int code;
		public Color color;
		Direction(int code, Color color){ this.code = code; this.color = color; }
		public int value(){ return code; }
		public Color color(){ return color; }
	};
	
	private Rectangle pad;
	private Direction direction;
	private boolean isSlippery;
	private double dx;

	public Racket(Pane pane) {
		pad = new Rectangle(0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
		pad.setTranslateX(PADDLE_X_OFFSET);
		pad.setTranslateY(PADDLE_Y_OFFSET);
		pad.setFill(PADDLE_COLOR);
		pane.getChildren().add(pad);
		dx = 0;
		direction = STILL;
		setSlippery(true);
	}

	public Rectangle getpad(){
		return pad;
	}

	public double getBoundsLeft(){
		return pad.getTranslateX();
	}
	public double getBoundsRight(){
		return getBoundsLeft() + PADDLE_WIDTH; 
	}
	public double getBoundsTop(){
		return pad.getTranslateY();
	}
	public double getBoundsBottom(){
		return pad.getTranslateY() + PADDLE_HEIGHT;
	}
	public double getXMiddle(){
		return getBoundsLeft() + PADDLE_WIDTH / 2;
	}
	public double getYatX(double x){
		return (x - getXMiddle()) * Math.tan(getTiltAngleRads()) + getBoundsTop();
	}
	public double getTiltAngleRads(){
		return direction.value() * PADDLE_TILT_ANGLE;
	}
	public double getTiltAngleDegrees(){
		return getTiltAngleRads() * 180 / Math.PI;
	}
	
	public void move(double x){
		pad.setTranslateX(getBoundsLeft() + x);
	}
	public void mouseMove(double x){
		x = Math.max(Math.min(x, WIDTH - PADDLE_WIDTH / 2), PADDLE_WIDTH / 2);
		dx = x - getXMiddle();
		pad.setTranslateX(x - PADDLE_WIDTH / 2);
	}
	public void setTilt(Direction direction){
		if(ALLOW_TILT){
			this.direction = direction;
			pad.setFill(direction.color());
			pad.setRotate(getTiltAngleDegrees());
		}
	}
	public int getDirection(){
		switch(direction){
		case LEFT:
			return -1;
		case STILL:
			return 0;
		case RIGHT:
			return 1;
		default:
			return 0;
		}
	}
	public void setSlippery(boolean value){
		isSlippery = value;
		pad.setStroke(value ? Color.BLUE : Color.BLACK);
	}
	
	public void collision(Ball ball){
		if(ball.getBoundsBottom() > getYatX(ball.getX())
				&& ball.getBoundsLeft() < getBoundsRight()
				&& ball.getBoundsRight() > getBoundsLeft()
				&& ball.getBoundsTop() < getBoundsBottom()
				&& ball.dy > 0)
		{
			ball.bounce(getTiltAngleRads() - Math.PI / 2);
			if(!isSlippery)
				ball.friction(dx, 0, PADDLE_FRICTION_FACTOR, 0);
			dx = 0;
		}
	}
}
