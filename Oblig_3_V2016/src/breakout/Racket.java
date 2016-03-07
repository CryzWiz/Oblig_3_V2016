package breakout;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Racket implements Settings{
	private Rectangle pad;
	double dx;
	double dy = PADDLE_Y_OFFSET;

	Racket(Pane pane) {
		pad = new Rectangle(PADDLE_X_OFFSET, PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		pad.setFill(PADDLE_COLOR);
		pane.getChildren().add(pad);
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

}
