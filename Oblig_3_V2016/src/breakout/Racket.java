package breakout;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Racket implements Settings{
	private Rectangle pad;
	double x;

	public Racket(Pane pane) {
		pad = new Rectangle(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		pad.setFill(PADDLE_COLOR);
		pane.getChildren().add(pad);
	}

	public Rectangle getpad(){
		return pad;
	}
	public void setpadX(double Xvalue){
		x = Xvalue;
		move(x);
	}
	public double getpadX(){
		return x;
	}
	public void move(double dx){
		pad.setTranslateX((getpadX() - PADDLE_X_OFFSET) - (PADDLE_WIDTH / 2));
	}
	public void collision(Ball ball){
		if(ball.getY() > PADDLE_Y_OFFSET - BALL_RADIUS - PADDLE_HEIGHT / 2
				&& ball.getX() < x + PADDLE_WIDTH / 2 + BALL_RADIUS
				&& ball.getX() > x - PADDLE_WIDTH / 2 + BALL_RADIUS
				&& ball.getY() < PADDLE_Y_OFFSET)
				ball.bounceY();
	}
}
