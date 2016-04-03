package states;

import breakout.Highscore;

public class VictoryState extends GameState {

	public VictoryState(){
		screenManager.setVictoryScreen();
		timer.pause();
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
