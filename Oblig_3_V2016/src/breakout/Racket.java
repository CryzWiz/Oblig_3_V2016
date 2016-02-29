package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Racket implements Settings{
	private Rectangle pad;
	int dx, dy;
	
	Racket() {
		dx = BreakOut.BALL_START_DX;
		dy = BreakOut.BALL_START_DY;
		pad = new Rectangle(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
	    pad.setFill(Color.GRAY);
	}
	
	public Rectangle getpad(){
		return pad;
	}
}
