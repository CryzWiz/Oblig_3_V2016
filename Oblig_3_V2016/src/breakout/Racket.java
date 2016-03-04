package breakout;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Racket extends Rectangle implements Settings{
	private Rectangle pad;
	double dx;
	double dy = PADDLE_Y_OFFSET;
	
	Racket() {
		pad = new Rectangle(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
	    pad.setFill(PADDLE_COLOR);
	}
	Racket(int PADDLE_X_OFFSET, int PADDLE_Y_OFFSET, int PADDLE_WIDTH, int PADDLE_HEIGHT
			, Color PADDLE_COLOR){
		pad = new Rectangle(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
	    pad.setFill(PADDLE_COLOR);
	}
	public Rectangle getpad(){
		return pad;
	}
	public void setpadX(double Xvalue){
		dx = Xvalue;
		move(dx);
	}
	public double getpadX(){
		return dx;
	}
	public void move(double dx){
		pad.setTranslateX((getpadX() - PADDLE_X_OFFSET) - (PADDLE_WIDTH / 2));
	}
	public void collision(Ball ball) {
		if (ball.getY() > (PADDLE_Y_OFFSET + 1)) {

			ball.dx *= -1;
			ball.dy *= -1;
			ball.bounceX();

		}
		
	}

}
