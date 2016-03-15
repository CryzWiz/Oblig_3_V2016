package breakout;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Racket implements Settings{
	public int LEFT = -1;
	public int STILL = 0;
	public int RIGHT = 1;
	
	private Rectangle pad;
	double dx;
	int direction;

	public Racket(Pane pane) {
		pad = new Rectangle(0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
		pad.setTranslateX(PADDLE_X_OFFSET);
		pad.setTranslateY(PADDLE_Y_OFFSET);
		pad.setFill(PADDLE_COLOR);
		pane.getChildren().add(pad);
		dx = 0;
		direction = STILL;
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
	
	public void move(double x){
		pad.setTranslateX(getBoundsLeft() + x);
	}
	public void mouseMove(double x){
		dx = x - getXMiddle();
		pad.setTranslateX(x - PADDLE_WIDTH / 2);
	}
	public void setRightTilt(){
		direction = RIGHT;
	}
	public void setNoTilt(){
		direction = STILL;
	}
	public void setLeftTilt(){
		direction = LEFT;
	}
	
	
	public void collision(Ball ball){
		if(ball.getBoundsBottom() > getBoundsTop()
				&& ball.getBoundsLeft() < getBoundsRight()
				&& ball.getBoundsRight() > getBoundsLeft()
				&& ball.getBoundsTop() < getBoundsBottom()
				&& ball.dy > 0)
		{
			ball.bounce(PADDLE_TILT_ANGLE * direction - Math.PI / 2);
			ball.friction(dx, 0, PADDLE_FRICTION_FACTOR, 0);
			dx = 0;
		}
	}
}
