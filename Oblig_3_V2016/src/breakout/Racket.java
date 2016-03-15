package breakout;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Racket implements Settings{
	private Rectangle pad;
	double dx;

	public Racket(Pane pane) {
		pad = new Rectangle(0, 0, PADDLE_WIDTH, PADDLE_HEIGHT);
		pad.setTranslateX(PADDLE_X_OFFSET);
		pad.setTranslateY(PADDLE_Y_OFFSET);
		pad.setFill(PADDLE_COLOR);
		pane.getChildren().add(pad);
		dx = 0;
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

	public void collision(Ball ball){
		if(ball.getBoundsBottom() > getBoundsTop()
				&& ball.getBoundsLeft() < getBoundsRight()
				&& ball.getBoundsRight() > getBoundsLeft()
				&& ball.getBoundsTop() < getBoundsBottom()
				&& ball.dy > 0)
				//&& ball.getY() < PADDLE_Y_OFFSET)
		{
			ball.bounceY();
			ball.friction(dx, 0, PADDLE_FRICTION, 0);
			dx = 0;
		}
	}
}
