package states;

import breakout.Highscore;
import breakout.Level;
import javafx.scene.input.MouseEvent;

public class VictoryState extends GameState {

	public VictoryState(){
		screenManager.setVictoryScreen();
		timer.pause();
		new Highscore("00:01:34", "Kenneth K");
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
	
	public void onEnterPress(){
		Highscore.saveScores("highscores.txt");
	};
	
	@Override
	public void onEscPress(){
		gameManager.menu();
	};
}
