package states;

public class PauseState extends GameState {

	public PauseState(){
		screenManager.setPauseScreen();
		timer.pause();
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
