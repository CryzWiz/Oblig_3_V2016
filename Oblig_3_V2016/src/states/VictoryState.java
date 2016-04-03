package states;

import breakout.Highscore;
import javafx.scene.input.MouseEvent;

public class VictoryState extends GameState {

	public VictoryState(){
		screenManager.setVictoryScreen();
		timer.pause();
	}

	@Override
	public void onMouseEvent(MouseEvent m){
		if(m.getEventType() == MouseEvent.MOUSE_CLICKED){
			/*Level.reset();
			timer.reset();
			ballManager.reset();
			brickManager.reset();
			gameManager.play();*/
		}
	}
	
	@Override
	public void onEnterPress(){
		Highscore.submit(timer);
		Highscore.saveScores(breakout.Settings.HIGHSCORE_FILE);
		gameManager.menu();
	};
	
	@Override
	public void onEscPress(){
		gameManager.menu();
	};
}
