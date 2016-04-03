package states;

import java.text.ParseException;

import breakout.Highscore;
import breakout.Level;
import breakout.Timer;
import javafx.scene.input.MouseEvent;

public class VictoryState extends GameState {

	public VictoryState(){
		screenManager.setVictoryScreen();
		timer.pause();
		try {
			new Highscore(Timer.parse("02:00:34"), "Kenneth K");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Highscore.saveScores(breakout.Settings.HIGHSCORE_FILE);
	};
	
	@Override
	public void onEscPress(){
		gameManager.menu();
	};
}
