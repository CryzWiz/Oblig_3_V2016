package states;

public class PauseState extends GameState {

	public PauseState(){
		sManager.setPauseScreen();
		pauseTimer();
	}

	@Override
	public void onSpacePressed(){
		game.play();
	}
	
	@Override
	public void onEnterPressed(){
		game.play();
	}
	
	@Override
	public void onEscPressed(){
		game.menu();
	}
}
