package states;

import breakout.Level;
import javafx.scene.input.MouseEvent;

public class VictoryState extends GameState {

	public VictoryState(){
		screenManager.setVictoryScreen();
		timer.pause();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			Level.reset();
			timer.reset();
			ballManager.reset();
			brickManager.reset();
			gameManager.play();
		}
	}
	
	@Override
	public void onEscPress(){
		System.exit(0);
	};
}
