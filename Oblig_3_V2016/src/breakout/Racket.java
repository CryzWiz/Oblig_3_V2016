package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Racket implements Settings{
	private Rectangle pad;
	double dx;
	double dy;
	
	Racket() {
		dx = BreakOut.BALL_START_DX;
		dy = BreakOut.BALL_START_DY;
		pad = new Rectangle(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
	    pad.setFill(Color.GRAY);
	}
	
	public Rectangle getpad(){
		return pad;
	}
	public void setpadX(double Xvalue){
		dx = Xvalue;
	}
	public double getpadX(){
		return dx;
	}

}
